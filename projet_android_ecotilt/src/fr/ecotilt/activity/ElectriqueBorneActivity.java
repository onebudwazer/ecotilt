package fr.ecotilt.activity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import fr.ecotilt.activity.asyntask.AsyncTaskCount;
import fr.ecotilt.activity.asyntask.AsyncTaskPompe;
import fr.ecotilt.activity.asyntask.ITaskCompletedCount;
import fr.ecotilt.activity.asyntask.ITaskCompletedPompe;
import fr.ecotilt.activity.asyntask.STATIC_URI;
import fr.ecotilt.appui.model.Count;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.rsc.R;

public class ElectriqueBorneActivity extends BaseActivity implements
		ITaskCompletedPompe, OnScrollListener, ITaskCompletedCount {

	private ArrayList<String>		valuesUi	= new ArrayList<String>();
	private ArrayAdapter<String>	adapter;
	private int						indexPage	= 0;
	private int						limitPage	= 5;
	private boolean					isloading	= false;
	private AsyncTaskPompe			atPompe		= new AsyncTaskPompe(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_electrique_borne);

		final ListView listview = (ListView) findViewById(R.id.listview);
		listview.setOnScrollListener(this);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, valuesUi);
		// adapter = new StableArrayAdapter(ElectriqueBorneActivity.this,
		listview.setAdapter(adapter);

		this.lannchPompeCount();
		this.launchPompeQuery();
	}

	private void launchPompeQuery() {
		try {
			indexPage = 0;
			URL[] url = new URL[] { new URL(STATIC_URI.URL_HTTP + "wspompe?p="
					+ indexPage) };
			atPompe.execute(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private void lannchPompeCount() {
		try {
			AsyncTaskCount asyncTaskCount = new AsyncTaskCount(this);
			URL[] url = new URL[] { new URL(STATIC_URI.URL_HTTP
					+ "wspompe?c=count") };
			asyncTaskCount.execute(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTaskCompleted(List<Pompe> listPompe) {
		for (Pompe pompe : listPompe) {
			valuesUi.add(pompe.toString());
		}
		adapter.notifyDataSetChanged();
		setProgressBarIndeterminateVisibility(false);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		int loadedItems = firstVisibleItem + visibleItemCount;

		if ((loadedItems == totalItemCount) && !isloading) {
			if (atPompe != null
					&& (atPompe.getStatus() == AsyncTask.Status.FINISHED)) {
				if (indexPage <= limitPage) {
					atPompe = new AsyncTaskPompe(this);
					try {
						indexPage++;
						URL[] url = new URL[] { new URL(STATIC_URI.URL_HTTP
								+ "wspompe?p=" + indexPage) };
						atPompe.execute(url);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							R.string.load_finish, Toast.LENGTH_SHORT).show();
				}

			}
		}

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	@Override
	public void onTaskError(String error) {
	}

	@Override
	public void onTaskCompleted(Count count) {
		if (count != null) {
			long nbrElement = count.getValue();
			double calculNbrPage = Math.ceil(nbrElement / 5.0);
			limitPage = (int) calculNbrPage;
		}
	}

	@Override
	public void onPreExecute() {
		setProgressBarIndeterminateVisibility(true);
	}
}
