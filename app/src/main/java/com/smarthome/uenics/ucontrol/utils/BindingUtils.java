package com.smarthome.uenics.ucontrol.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.smarthome.uenics.ucontrol.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;



public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    public static SimpleDateFormat showDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static SimpleDateFormat commitDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static RequestOptions options = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.agri_logo)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform();

    /*@BindingAdapter({"lotAdapter"})
    public static void addNewsItems(RecyclerView recyclerView, MutableLiveData<List<News>> blogs) {
        NewsAdapter adapter = (NewsAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(blogs.getValue());
        }
    }*/

  /*  @BindingAdapter({"stages"})
    public static void addStages(RecyclerView recyclerView, MutableLiveData<Store> store) {
        aStage selectionAdapter = (aStage) recyclerView.getAdapter();
        if (selectionAdapter!= null && store.getValue() != null) {
            selectionAdapter.addItems((ArrayList<mStage>) store.getValue().getStages());
        }
    }

    @BindingAdapter({"commodities"})
    public static void addCommodities(RecyclerView recyclerView, MutableLiveData<ArrayList<mCommodity>> commodities) {
        CommodityAdapter commodityAdapter = (CommodityAdapter) recyclerView.getAdapter();
        if (commodityAdapter!= null && commodities.getValue() != null) {
            commodityAdapter.updateData(commodities.getValue());
        }
    }
*/
    @BindingAdapter(value = {"currentPage","isAtZeroIndex","isAtLastIndex"},requireAll = false)
    public static void addOnPageChangeListener(ViewPager viewPager,
                                               MutableLiveData<Integer> currentPage,
                                               MutableLiveData<Boolean> isAtZeroIndex,
                                               MutableLiveData<Boolean> isAtLastIndex) {


        int position = viewPager.getCurrentItem();
        if (currentPage.getValue() != null &&
                (currentPage.getValue()!= position || currentPage.getValue()== 0)) {
            if (currentPage.getValue()== 0){
                if (isAtZeroIndex != null ) {
                    isAtZeroIndex.setValue(position == 0);
                }
                if (isAtLastIndex != null ) {
                    isAtLastIndex.setValue(position == Objects.requireNonNull(viewPager.getAdapter()).getCount() - 1);
                }
            }
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentPage.setValue(position);

                    if (isAtZeroIndex != null ) {
                        isAtZeroIndex.setValue(position == 0);
                    }
                    if (isAtLastIndex != null ) {
                        isAtLastIndex.setValue(position == Objects.requireNonNull(viewPager.getAdapter()).getCount() - 1);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            viewPager.setCurrentItem(currentPage.getValue());

        }





    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).apply(options).into(imageView);
    }


    @BindingAdapter(value = {"datePick", "maxDate", "minDate","OntextDateChanged"}, requireAll = false)
    public static void bindTextViewDateClicks(final TextView textView, final MutableLiveData<Date> date,
                                              final MutableLiveData<Date> maxDate,
                                              final MutableLiveData<Date> minDate,
                                              OnDateChangeListener listener) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate(textView.getContext(), date, maxDate, minDate,listener);
            }
        });
    }

    @BindingAdapter(value = {"textDate", "nullText"}, requireAll = true)
    public static void bindTextViewDate(final TextView textView, final MutableLiveData<Date> date,
                                        String nullText) {
        if (date != null && date.getValue() != null) {
            textView.setText(String.format("%s", showDateFormat.format(date.getValue())));
        } else if (nullText != null) {
            textView.setText(nullText);
        }

    }

    public interface OnDateChangeListener{
        void onDateChanged(Date date);
    }

    private static void selectDate(Context context, final MutableLiveData<Date> date,
                                   final MutableLiveData<Date> maxDate,
                                   final MutableLiveData<Date> minDate,
                                   OnDateChangeListener listener) {
        final Calendar calBefore = Calendar.getInstance();


        if (date != null && date.getValue() != null)
            calBefore.setTime(date.getValue());

        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker dpView, int year, int monthOfYear, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, monthOfYear);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                assert date != null;
                date.setValue(cal.getTime());
                if (listener != null ){
                    listener.onDateChanged(cal.getTime());
                }
                //date.notifyChange();
            }
        }, calBefore.get(Calendar.YEAR), calBefore.get(Calendar.MONTH), calBefore.get(Calendar.DAY_OF_MONTH));

        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "CLEAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                assert date != null;
                date.setValue(null);
                if (listener != null){
                    listener.onDateChanged(null);
                }
            }
        });


        if (minDate != null && minDate.getValue()!=null)
            dialog.getDatePicker().setMinDate(minDate.getValue().getTime());
        if (maxDate != null && maxDate.getValue()!=null)
            dialog.getDatePicker().setMaxDate(maxDate.getValue().getTime());

        dialog.show();
    }




}
