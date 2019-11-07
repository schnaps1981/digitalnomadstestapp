package com.testapp.ui.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.testapp.R;
import com.testapp.data.network.utils.NetworkState;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NetworkItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvNetworkStateErrorMsg) TextView tvNetworkStateErrorMsg;
    @BindView(R.id.btnNetworkStateRetry) Button  btnNetworkStateRetry;
    @BindView(R.id.pbNetworkStateProgress) ProgressBar pbNetworkStateProgress;

    public NetworkItemViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(NetworkState networkState)
    {
        if (networkState.getStatus() == NetworkState.Status.NO_MORE_DATA)
        {
            pbNetworkStateProgress.setVisibility(View.GONE);
            btnNetworkStateRetry.setVisibility(View.GONE);

            tvNetworkStateErrorMsg.setVisibility(View.VISIBLE);
            tvNetworkStateErrorMsg.setText(networkState.getMsg());
            return;
        }

        if (networkState.getStatus() == NetworkState.Status.RUNNING)  {
            pbNetworkStateProgress.setVisibility(View.VISIBLE);
        }
        else
        {
            pbNetworkStateProgress.setVisibility(View.GONE);
        }

        if (networkState.getStatus() == NetworkState.Status.FAILED)
        {
            btnNetworkStateRetry.setVisibility(View.VISIBLE);
            tvNetworkStateErrorMsg.setVisibility(View.VISIBLE);
        }
        else
        {
            btnNetworkStateRetry.setVisibility(View.GONE);
            tvNetworkStateErrorMsg.setVisibility(View.GONE);
        }

    }
}
