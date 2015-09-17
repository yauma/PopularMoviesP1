package com.example.jaimequeralt.popularmovies;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private GridView gridview;
    private ImageAdapter imageAdapter;
    private ArrayList<String> mListImages;
    private final String API_KEY = "";
    private String filter = "popular";
    private JsonObjectRequest jsObjRequest;
    private ActionBar mActionBar;
    private Movie movie;
    private ArrayList<Movie> listMovies;


    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setTitle("Most Popular Movies");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_gridview, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.most_polular) {
            if (filter.equals("top_rated")) {
                filter = "popular";
                mActionBar.setTitle("Most Popular Movies");
                loadGridViewFromAPI();
            }
        }
        if (id == R.id.top_rated) {
            if (filter.equals("popular")) {
                filter = "top_rated";
                mActionBar.setTitle("Top Rated Movies");
                loadGridViewFromAPI();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gridview, container, false);

        gridview = (GridView) rootView.findViewById(R.id.gridview);

        loadGridViewFromAPI();


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(getActivity(),DetailMovieActivity.class);
                intent.putExtra("Movie", listMovies.get(position));
                startActivity(intent);
            }
        });




        return rootView;
    }

    private void loadGridViewFromAPI() {
        final String POPULAR_MOVIES_BASE_URL =
                "http://api.themoviedb.org/3/movie/" + filter + "?";
        final String API_KEY_PARAM = "api_key";

        Uri builtUri = Uri.parse(POPULAR_MOVIES_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build();

        String url = builtUri.toString();

        jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        mListImages = parseJsonObject(response);
                        imageAdapter = new ImageAdapter(getActivity(), mListImages);
                        gridview.setAdapter(imageAdapter);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getActivity()).addToRequestQueue(jsObjRequest);
    }

    private ArrayList<String> parseJsonObject(JSONObject response) {
        movie = new Movie();
        mListImages = new ArrayList<String>();
        listMovies = new ArrayList<>();
        try {
            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject posterObj = results.getJSONObject(i);
                String originalTitle = posterObj.getString("original_title");
                String posterPath = posterObj.getString("poster_path");
                String overview = posterObj.getString("overview");
                String releaseDate = posterObj.getString("release_date");
                int average = posterObj.getInt("vote_average");
                movie = new Movie(originalTitle,posterPath,overview,releaseDate,average);
                listMovies.add(movie);
                mListImages.add(posterPath);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mListImages;
    }

}