var fs = require('fs');
var system = require('system');

var host = 'chesstempo.com';
var deltaSleep = 4000;
var minSleep = 1000;

if (system.args.length != 5) {
    system.stderr.writeLine("Usage: games_retriever.js <index file path> <index> <partitions> <output file path>");
    phantom.exit(1);
}

var indexFilePath = system.args[1];
var index = system.args[2];
var partitions = system.args[3];
var outputFilePath = system.args[4];
var indexFd = fs.open(indexFilePath, 'r');
var outputFd = fs.open(outputFilePath, 'w');
var page = require('webpage').create();
var counter = 0;
var indexRecord = null;

var consumeNextLine = function() {
	setTimeout(function() {
		try {
			for (var line; (line = indexFd.readLine()) != null;) {
				if (counter%partitions == index) {
					indexRecord = JSON.parse(line);
					counter++;
					page.open('http://' + host + indexRecord.link);
					return;
				} else {
					counter++;
				}
			}
			indexFd.close();
			outputFd.close();
			phantom.exit(0);
		} catch(exception) {
			console.error(exception);
			indexFd.close();
			outputFd.close();
			phantom.exit(0);
		}
	}, Math.random() * deltaSleep + minSleep);
}

var pageParser = function(indexRecord) {
    var text = function(element) {
        return $(element).text().replace(/\s+/g, ' ').trim();
    };
    var gameDescription = $('div[id|="gameDescBody"]');
    data = {
        player1: {
            name: text($(gameDescription).find('tbody > tr:nth-child(1) td:nth-child(1) a:nth-child(1)')),
            link: text($(gameDescription).find('tbody > tr:nth-child(1) td:nth-child(1) a:nth-child(1)'))
        },
        player2: {
            name: text($(gameDescription).find('tbody > tr:nth-child(1) td:nth-child(1) a:nth-child(2)')),
            link: $(gameDescription).find('tbody > tr:nth-child(1) td:nth-child(1) a:nth-child(2)').attr('href')
        },
        date: text($(gameDescription).find('tbody > tr:nth-child(2) td:nth-child(2)')),
        event: text($(gameDescription).find('tbody > tr:nth-child(3) td:nth-child(2)')),
        round: text($(gameDescription).find('tbody > tr:nth-child(4) td:nth-child(2)')),
        result: text($(gameDescription).find('tbody > tr:nth-child(5) td:nth-child(2)')),
        index: indexRecord,
        games: []
    };
    var games = $('div[id|="board-moves"]');
    $(games).find('span[class|="ct-board-move-movetext"]').each(function(index) {
        data.games.push($(this).text().replace(/\s+/g, ' ').trim());
    });
    return data;
}

page.onLoadFinished = function(status) {
    try {
        if (status === 'success') {
            console.info(page.url + ' OK');
            page.injectJs('../public/jquery/jquery-1.10.2.min.js');
            var data = page.evaluate(pageParser, indexRecord);
            outputFd.writeLine(JSON.stringify(data));
            outputFd.flush();
        } else {
            console.error(page.url + ' FAIL connection error');
        }
    } catch(exception) {
        console.error(page.url + ' FAIL ' + exception);
    }
    consumeNextLine();
}

page.onConsoleMessage = function (msg, line, source) {
    console.log(msg);
};

page.onResourceRequested = function (requestData, request) {
	if (!new RegExp(host).test(requestData['url'])) {
		request.abort();
	}
};

consumeNextLine();