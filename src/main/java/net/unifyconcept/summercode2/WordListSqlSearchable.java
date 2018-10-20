HelloSharedprefs
package com.example.android.hellosharedprefs;
	
	import android.graphics.drawable.ColorDrawable;
	import android.support.v4.content.ContextCompat;
	import android.support.v7.app.AppCompatActivity;
	import android.os.Bundle;
	import android.view.View;
	import android.widget.TextView;
 	public class MainActivity extends AppCompatActivity {
	// Current count.
	private int mCount = 0;
	// Current background color.
	private int mColor;
	// Text view to display both count and color.
	private TextView mShowCountTextView;
	
	// Key for current count
	private final String COUNT_KEY = "count";
	// Key for current color
	private final String COLOR_KEY = "color";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	// Initialize views, color
	mShowCountTextView = (TextView) findViewById(R.id.count_textview);
	mColor = ContextCompat.getColor(this, R.color.default_background);
	
	// Restore the saved state. See onSaveInstanceState() for what gets saved.
	if (savedInstanceState != null) {
	
	mCount = savedInstanceState.getInt(COUNT_KEY);
	if (mCount != 0) {
	mShowCountTextView.setText(String.format("%s", mCount));
	}
	
	mColor = savedInstanceState.getInt(COLOR_KEY);
	mShowCountTextView.setBackgroundColor(mColor);
	}
	}
public void changeBackground(View view) {
	int color = ((ColorDrawable) view.getBackground()).getColor();
	mShowCountTextView.setBackgroundColor(color);
	mColor = color;
	}
 	public void countUp(View view) {
	mCount++;
	mShowCountTextView.setText(String.format("%s", mCount));
	}
@Override
	protected void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	
	outState.putInt(COUNT_KEY, mCount);
	outState.putInt(COLOR_KEY, mColor);
	}
public void reset(View view) {
	// Reset count
	mCount = 0;
	mShowCountTextView.setText(String.format("%s", mCount));
	
	// Reset color
	mColor = ContextCompat.getColor(this, R.color.default_background);
	mShowCountTextView.setBackgroundColor(mColor);
	}
	}

WordListSqlSearchable

package com.android.example.wordlistsql;
	
	import android.content.Context;
	import android.support.v7.widget.RecyclerView;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.Button;
	import android.widget.TextView;
class WordViewHolder extends RecyclerView.ViewHolder {
	public final TextView wordItemView;
	Button delete_button;
	Button edit_button;
	
	public WordViewHolder(View itemView) {
	super(itemView);
	wordItemView = (TextView) itemView.findViewById(R.id.word);
	delete_button = (Button)itemView.findViewById(R.id.delete_button);
	edit_button = (Button)itemView.findViewById(R.id.edit_button);
	}
	}
	
	private static final String TAG = WordListAdapter.class.getSimpleName();
	
	public static final String EXTRA_ID = "ID";
	public static final String EXTRA_WORD = "WORD";
	
	private final LayoutInflater mInflater;
	Context mContext;
	
	public WordListAdapter(Context context) {
	mInflater = LayoutInflater.from(context);
	mContext = context;
	}
	
	@Override
	public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	View itemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
	return new WordViewHolder(itemView);
	}
	
	@Override
	public void onBindViewHolder(WordViewHolder holder, int position) {
	holder.wordItemView.setText("placeholder");
	}
	
	@Override
	public int getItemCount() {
	// Placeholder so we can see some mock data.
	return 10;
	}
	}

WordListClient

al String EXTRA_REPLY =
	"com.example.android.wordlistsqlwithcontentprovider.REPLY";
	
	private static final String TAG = EditWordActivity.class.getSimpleName();
	
	int mId = MainActivity.WORD_ADD;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_edit_word);
	
	mEditWordView = (EditText) findViewById(R.id.edit_word);
	
	// Get data sent from calling activity
	Bundle extras = getIntent().getExtras();
	
	// If we are passed content, fill it in for the user to edit.
	if (extras != null) {
	int id = extras.getInt(WordListAdapter.EXTRA_ID, NO_ID);
	String word = extras.getString(WordListAdapter.EXTRA_WORD, NO_WORD);
	if (id != NO_ID && !word.equals(NO_WORD)) {
	mId = id;
	mEditWordView.setText(word);
	}
	} // Otherwise, start with empty fields.
	}
	
	public void returnReply(View view) {
	String word = ((EditText) findViewById(R.id.edit_word)).getText().toString();
	
	/* Create a new intent for the reply, add the reply message to it as an extra,
	set the intent result, and close the activity. */
	Intent replyIntent = new Intent();
	replyIntent.putExtra(EXTRA_REPLY, word);
	replyIntent.putExtra(WordListAdapter.EXTRA_ID, mId);
	setResult(RESULT_OK, replyIntent);
	finish();
	}
	}

WordListLoader

package org.apache.lucene.analysis;
	
	
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.Reader;
	import java.nio.charset.Charset;
	import java.util.ArrayList;
	import java.util.List;
	
	import org.apache.lucene.util.IOUtils;
public class WordlistLoader {
	
	private static final int INITIAL_CAPACITY = 16;
public static CharArraySet getWordSet(Reader reader, CharArraySet result) throws IOException {
	BufferedReader br = null;
	try {
	br = getBufferedReader(reader);
	String word = null;
	while ((word = br.readLine()) != null) {
	result.add(word.trim());
	}
	}
	finally {
	IOUtils.close(br);
	}
	return result;
	}
public static CharArraySet getWordSet(Reader reader) throws IOException {
	return getWordSet(reader, new CharArraySet(INITIAL_CAPACITY, false));
	}
 	public static CharArraySet getWordSet(Reader reader, String comment) throws IOException {
	return getWordSet(reader, comment, new CharArraySet(INITIAL_CAPACITY, false));
	}
public static CharArraySet getWordSet(Reader reader, String comment, CharArraySet result) throws IOException {
	BufferedReader br = null;
	try {
	br = getBufferedReader(reader);
	String word = null;
	while ((word = br.readLine()) != null) {
	if (word.startsWith(comment) == false){
	result.add(word.trim());
	}
	}
	}
	finally {
	IOUtils.close(br);
	}
	return result;
	}
public static CharArraySet getSnowballWordSet(Reader reader, CharArraySet result)
	throws IOException {
	BufferedReader br = null;
	try {
	br = getBufferedReader(reader);
	String line = null;
	while ((line = br.readLine()) != null) {
	int comment = line.indexOf('|');
	if (comment >= 0) line = line.substring(0, comment);
	String words[] = line.split("\\s+");
	for (int i = 0; i < words.length; i++)
	if (words[i].length() > 0) result.add(words[i]);
	}
	} finally {
	IOUtils.close(br);
	}
	return result;
	}
public static CharArraySet getSnowballWordSet(Reader reader) throws IOException {
	return getSnowballWordSet(reader, new CharArraySet(INITIAL_CAPACITY, false));
	}
 	public static CharArrayMap<String> getStemDict(Reader reader, CharArrayMap<String> result) throws IOException {
	BufferedReader br = null;
	try {
	br = getBufferedReader(reader);
	String line;
	while ((line = br.readLine()) != null) {
	String[] wordstem = line.split("\t", 2);
	result.put(wordstem[0], wordstem[1]);
	}
	} finally {
	IOUtils.close(br);
	}
	return result;
	}
public static List<String> getLines(InputStream stream, Charset charset) throws IOException{
	BufferedReader input = null;
	ArrayList<String> lines;
	boolean success = false;
	try {
	input = getBufferedReader(IOUtils.getDecodingReader(stream, charset));
	
	lines = new ArrayList<>();
	for (String word=null; (word=input.readLine())!=null;) {
	// skip initial bom marker
	if (lines.isEmpty() && word.length() > 0 && word.charAt(0) == '\uFEFF')
	word = word.substring(1);
	// skip comments
	if (word.startsWith("#")) continue;
	word=word.trim();
	// skip blank lines
	if (word.length()==0) continue;
	lines.add(word);
	}
	success = true;
	return lines;
	} finally {
	if (success) {
	IOUtils.close(input);
	} else {
	IOUtils.closeWhileHandlingException(input);
	}
	}
	}
	
	private static BufferedReader getBufferedReader(Reader reader) {
	return (reader instanceof BufferedReader) ? (BufferedReader) reader
	: new BufferedReader(reader);
	}
	
	}
