chess
=====

Install
-------
#Install [PhantomJS](http://phantomjs.org/)
On mac install using brew.
<pre>
	brew install phantomjs
</pre>

Games download
--------------
The games can be downloaded in two steps. First building the list of
all games. To do execute the following script. For example:
<pre>
	phantomjs src/main/js/chess_tempo/get_index.js \
		data/chess_tempo_index_20140101.jsons
</pre>
. Second, downloading the games from the built index. The index can
be downloaded inparallel by running multiples instances of the script,
indicating the index and the number of scripts to be executed.
<pre>
for i in {0..9}; do
	phantomjs src/main/js/chess_tempo/games_retriever.js \
		data/chess_tempo_index_20140101.jsons \
		$i \
		10 \
		data/games_20140101_$i.jsons&
done
</pre>