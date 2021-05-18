package com.smarthome.uenics.ucontrol.ui.imageDataCollection.fragmentDataCollection;

import androidx.lifecycle.ViewModelProvider;

import com.smarthome.uenics.ucontrol.BR;
import com.smarthome.uenics.ucontrol.R;
import com.smarthome.uenics.ucontrol.databinding.FragmentUploadBinding;
import com.smarthome.uenics.ucontrol.ui.base.BaseFragment;
import com.smarthome.uenics.ucontrol.ui.base.ViewModelProviderFactory;
import com.smarthome.uenics.ucontrol.ui.imageDataCollection.VMImageDataCollection;

import javax.inject.Inject;


public class UploadFragment extends BaseFragment<FragmentUploadBinding, VMImageDataCollection> {

    @Inject
    ViewModelProviderFactory factory;

    FragmentUploadBinding fragmentUploadBinding;

    VMImageDataCollection vmMainDataCollection;
    @Override
    public int getBindingVariable() {
        return BR.vmMain;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_upload;
    }

    @Override
    public VMImageDataCollection getViewModel() {
        vmMainDataCollection = new ViewModelProvider(getBaseActivity(),factory).get(VMImageDataCollection.class);
        return vmMainDataCollection;
    }

}
