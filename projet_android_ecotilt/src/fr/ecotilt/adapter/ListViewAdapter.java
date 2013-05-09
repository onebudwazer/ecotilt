package fr.ecotilt.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import fr.ecotilt.rsc.R;

public class ListViewAdapter extends EndlessAdapter {
	// private RotateAnimation rotate = null;
	private View	pendingView	= null;

	public ListViewAdapter(Context ctxt, ArrayList<String> list) {
		super(new ArrayAdapter<String>(ctxt, R.layout.itemlistview, list));
		// rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
		// 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		// rotate.setDuration(600);
		// rotate.setRepeatMode(Animation.RESTART);
		// rotate.setRepeatCount(Animation.INFINITE);
	}

	@Override
	protected View getPendingView(ViewGroup parent) {
		View row = LayoutInflater.from(parent.getContext()).inflate(
				R.layout.pending, null);
		pendingView = row.findViewById(android.R.id.text1);
		pendingView.setVisibility(View.GONE);
		// pendingView = row.findViewById(R.id.throbber);
		pendingView.setVisibility(View.VISIBLE);
		startProgressAnimation();
		return (row);
	}

	@Override
	protected boolean cacheInBackground() {
		return (getWrappedAdapter().getCount() < 95);
	}

	@Override
	protected void appendCachedData() {
		// if (getWrappedAdapter().getCount() < 75) {
		// @SuppressWarnings("unchecked")
		// ArrayAdapter<Integer> a = (ArrayAdapter<Integer>)
		// getWrappedAdapter();
		//
		// for (int i = 0; i < 25; i++) {
		// a.add(a.getCount());
		// }
		// }
	}

	void startProgressAnimation() {
		// if (pendingView != null) {
		// pendingView.startAnimation(rotate);
		// }
	}
}