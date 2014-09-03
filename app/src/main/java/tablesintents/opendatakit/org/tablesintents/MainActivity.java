package tablesintents.opendatakit.org.tablesintents;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    /**
     * These should match Constants.IntentKeys in Tables. Should maybe jar
     * these up later or otherwise export them.
     */
    protected static class BundleArgs {
        public static final String APP_NAME = "appName";
        public static final String TABLE_ID = "tableId";
        public static final String FILE_NAME = "filename";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        TextView mTableIdView;
        TextView mFileNameView;
        TextView mAppNameView;

        Button mLaunchListButton;
        Button mLaunchHomeScreenButton;


        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(
                    R.layout.fragment_main,
                    container,
                    false);

            this.mTableIdView =
                    (TextView) rootView.findViewById(R.id.table_id_chooser);
            this.mFileNameView =
                    (TextView) rootView.findViewById(R.id.file_name_chooser);
            this.mAppNameView =
                    (TextView) rootView.findViewById(R.id.app_name_chooser);

            this.mLaunchHomeScreenButton =
                    (Button) rootView.findViewById(R.id.action_launch_list);
            this.mLaunchListButton = (Button) rootView.findViewById(
                    R.id.action_launch_home_screen);

            return rootView;
        }

        protected String getFileName() {
            return this.mFileNameView.getText().toString().trim();
        }

        protected String getTableId() {
            return this.mTableIdView.getText().toString().trim();
        }

        protected String getAppName() {
            return this.mAppNameView.getText().toString().trim();
        }

        protected void addFileNameToBundle(Bundle bundle) {
            String fileName = this.getFileName();
            bundle.putString(BundleArgs.FILE_NAME, fileName);
        }

        protected void addTableIdToBundle(Bundle bundle) {
            String tableId = this.getTableId();
            bundle.putString(BundleArgs.TABLE_ID, tableId);
        }

        protected void addAppNameToBundle(Bundle bundle) {
            String appName = this.getAppName();
            bundle.putString(BundleArgs.APP_NAME, appName);
        }
    }
}
