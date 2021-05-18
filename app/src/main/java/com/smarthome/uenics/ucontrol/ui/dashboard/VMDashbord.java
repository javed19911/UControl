package com.smarthome.uenics.ucontrol.ui.dashboard;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.smarthome.uenics.ucontrol.R;
import com.smarthome.uenics.ucontrol.data.DataManager;
import com.smarthome.uenics.ucontrol.data.model.others.Home;
import com.smarthome.uenics.ucontrol.ui.base.BaseViewModel;
import com.smarthome.uenics.ucontrol.ui.dashboard.fragment.HomeFragment;
import com.smarthome.uenics.ucontrol.ui.dashboard.fragment.NoHomeFragment;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.fragmentDataCollection.CameraFragment;
import com.smarthome.uenics.ucontrol.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;


public class VMDashbord extends BaseViewModel<iDashboard> {
    private FragmentManager fragmentManager;

    public LiveData<List<Home>> smartHomes = new MutableLiveData<>(new ArrayList<Home>());
    public MutableLiveData<Home> selectedHome = new MutableLiveData<>();

    public VMDashbord(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        smartHomes = dataManager.getAllHomes();
    }

    public void onCommoditySelected(android.widget.AdapterView parent,int position) {
        selectedHome.setValue((Home) parent.getSelectedItem());
        getCommodityDetails();
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        if (fragmentManager.getFragments().size() == 0) {

//            if(smartHomes.getValue().size() >0){
//                showHomeFragment();
//            }else {
                showNoHomeFragment();
//            }
        }
    }


    public void showNoHomeFragment(){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, new NoHomeFragment());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void showHomeFragment(){
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, new HomeFragment());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (fragmentManager.getFragments().size() != 0) {
            ft.addToBackStack("HomeFragment");
        }
        ft.commit();
    }

    public void showAddHomeDialog(){
        if (getNavigator() != null) {
            getNavigator().showAddHomeDialog();
        }
    }

    private void getCommodityDetails() {


       /* setIsLoading(true);
        //lots.setValue(new ArrayList<>());
        getDataManager().getAuctionDetails(date.getValue(),selectedAuctioneer.getValue())
                .enqueue(new Callback<AuctionResponse>() {
                    @Override
                    public void onResponse(Call<AuctionResponse> call, Response<AuctionResponse> response) {

                        if (response.isSuccessful()) {
                            AuctionResponse auctionResponse = response.body();
                            if (auctionResponse.getStatus().equalsIgnoreCase("success")) {
                                auctions.setValue(auctionResponse.getAuctions());
                                if (auctionResponse.getAuctions() != null &&
                                        auctionResponse.getAuctions().size() >1 &&
                                        selectedAuction.getValue() != null &&
                                        selectedAuction.getValue().getId() != auctionResponse.getAuctions().get(0).getId()){

                                    selectedAuction.setValue(auctionResponse.getAuctions().get(0));
                                    getLotDetails();
                                }
                            }else{
                                lots.setValue(new ArrayList<>());
                                if (getNavigator() != null) {
                                    getNavigator().showMessage(auctionResponse.getError_msg());
                                }
                            }
                        }else{
                            lots.setValue(new ArrayList<>());
                            if (getNavigator() != null) {
                                getNavigator().showMessage("Data Error....");
                            }
                        }
                        setIsLoading(false);
                    }

                    @Override
                    public void onFailure(Call<AuctionResponse> call, Throwable t) {
                        lots.setValue(new ArrayList<>());
                        setIsLoading(false);
                        if (getNavigator() != null) {
                            getNavigator().handleError(t);
                        }
                    }
                });*/

    }

}
