package practice.practice_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.list_view);

        // Create sample data
        User sampleUser = new User();
        sampleUser.userName = "Steph";
        sampleUser.profilePictureUrl = "https://i.imgur.com/tGbaZCY.jpg";

        Post samplePost = new Post();
        samplePost.user = sampleUser;
        samplePost.text = "Won won!";

        // Get singleton instance of database
        PostsDatabaseHelper databaseHelper = PostsDatabaseHelper.getInstance(this);

        // Add sample post to the database
        databaseHelper.addPost(samplePost);

        // Get all posts from database
        List<Post> posts = databaseHelper.getAllPosts();
        String[] postTexts = new String[]{};
        ArrayList<String> temp = new ArrayList<String>();
        for (Post post : posts) {
            // do something
            temp.add(post.text);
        }
        postTexts = temp.toArray(new String[temp.size()]);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,postTexts);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
