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

    protected static class IntentNames {
        public static final String MAIN_ACTIVITY =
                "org.opendatakit.tables.activities.Launcher";
        public static final String TABLE_DISPLAY_ACTIVITY =
                "org.opendatakit.tables.activities.TableDisplayActivity";

    }

    /**
     * These should match Constants.IntentKeys in Tables. Should maybe jar
     * these up later or otherwise export them.
     */
    protected static class BundleArgs {
        public static final String APP_NAME = "appName";
        public static final String TABLE_ID = "tableId";
        public static final String FILE_NAME = "filename";
        public static final String TABLE_DISPLAY_TYPE = "tableDisplayViewType";
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

            this.mLaunchHomeScreenButton = (Button) rootView.findViewById(
                    R.id.action_launch_home_screen);
            this.mLaunchListButton = (Button) rootView.findViewById(
                    R.id.action_launch_list);

            this.attachListeners();

            return rootView;
        }

        /**
         * Add the listeners to the buttons that will handle the intents.
         */
        private void attachListeners() {
            this.mLaunchHomeScreenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(IntentNames.MAIN_ACTIVITY);
                    Bundle args = new Bundle();
                    addKeysToBundle(args);
                    intent.putExtras(args);
                    getActivity().startActivity(intent);
                }
            });

            this.mLaunchListButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =
                            new Intent(IntentNames.TABLE_DISPLAY_ACTIVITY);
                    Bundle args = new Bundle();
                    addKeysToBundle(args);
                    addListTypeToBundle(args);
                    intent.putExtras(args);
                    getActivity().startActivity(intent);
                }
            });
        }

        /**
         * Add all the keys to the bundle.
         * @param bundle
         */
        protected void addKeysToBundle(Bundle bundle) {
            this.addAppNameToBundle(bundle);
            this.addFileNameToBundle(bundle);
            this.addTableIdToBundle(bundle);
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

        protected void addListTypeToBundle(Bundle bundle) {
            bundle.putString(BundleArgs.TABLE_DISPLAY_TYPE, "LIST");
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
