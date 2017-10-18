<h3>This sample app can fetch json from url and show them in list view using built-in Java & Android Methods .</h3>

<p>In this app , I have used Java's <a href="https://docs.oracle.com/javase/8/docs/api/java/net/URL.html">URL</a> and 
<a href="https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html">HttpURLConnection</a>
to get Input Stream from given url
and <a href="https://developer.android.com/reference/org/json/JSONArray.html">JSONArray</a>
and <a href="https://developer.android.com/reference/org/json/JSONObject.html">JSONObject</a>to fetch JSON .

<h4>Procedures:</h4>
<ul>
<li>Give Internet Permission at <a href="https://github.com/bhattaraisubash/JsonFetch/blob/master/app/src/main/AndroidManifest.xml">
AndroidManifest.xml</a></li><code>android.permission.INTERNET</code>
<li>Create Post Entity &raquo;
<a href="https://github.com/bhattaraisubash/JsonFetch/blob/master/app/src/main/java/com/bhattaraisubash/jsonfetch/entity/Post.java">
Post.java</a></li>
<li>Create A Adapter For List View &raquo;
<a href="https://github.com/bhattaraisubash/JsonFetch/blob/master/app/src/main/java/com/bhattaraisubash/jsonfetch/adapter/ListAdapter.java">
ListAdapter.java</a><br/><code>
public class ListAdapter extends ArrayAdapter<Post> {
    ...</code></li>
    <li>Create a inner class JsonFetch.java in 
    <a href="https://github.com/bhattaraisubash/JsonFetch/blob/master/app/src/main/java/com/bhattaraisubash/jsonfetch/MainActivity.java">
    MainActivity.java</a> which extends AsyncTask.<br/>
    <code>private class FetJson extends AsyncTask<String, Void, List<Post>> {
    ...</code><br/>
    In <code>onPostExecute(..)</code> method , adapter is set for listView .
    </li>
    <li>Now Execute JsonFetch class inside <code>onCreate</code> method of
     <a href="https://github.com/bhattaraisubash/JsonFetch/blob/master/app/src/main/java/com/bhattaraisubash/jsonfetch/MainActivity.java">
    MainActivity.java</a> passing url as parameter.<br/>
    <code>
    new FetJson().execute("https://jsonplaceholder.typicode.com/posts");</code>
</ul>
<p>Finally list view shows available data .
