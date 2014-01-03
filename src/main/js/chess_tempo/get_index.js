var fs = require('fs');
var system = require('system');

var host = 'chesstempo.com';
var seed_path = '/game-database-htmlonly.html';
var deltaSleep = 1000;
var minSleep = 1000;

if (system.args.length != 2 && system.args.length != 3) {
    system.stderr.writeLine("Usage: get_index.js <output file path> [seed_path]");
    phantom.exit(1);
}

var outputFilePath = system.args[1];
var fd = fs.open(outputFilePath, 'a');
var page = require('webpage').create();
if (system.args.length == 3) {
	seed_path = system.args[2];
}

var consumeNextUrl = function(path) {
	if (path == null) {
        fd.close();
        phantom.exit(0);
	} else {
		setTimeout(function() {
			page.open('http://' + host + path);
		}, Math.random() * deltaSleep + minSleep);
	}
};

var pageParser = function() {
    var headers = [];
    var records = [];
    $('table > tbody tr').each(function(_){
        if (headers.length == 0) {
            $(this).find('th').each(function(index) {
                headers.push($(this).text().replace(/\s+/g, ' ').trim());
            });
        } else {
            var record = {};
            $(this).find('td').each(function(index) {
                if (headers[index] == '#') {
                    record['link'] = $(this).find('a').attr('href').replace(/\s+/g, ' ').trim();
                } else {
                    record[headers[index]] = $(this).text().replace(/\s+/g, ' ').trim();
                }
            });
            records.push(record);
        }
    });
    var next = $('*[id|="yui-main"] > div > div > a:contains("Next")');
    var nextUrl = next.length == 1 ? $(next[0]).attr('href') : null;
    return {
        nextUrl: nextUrl,
        records: records
    };
}

page.onLoadFinished = function(status) {
    try {
        if (status === 'success') {
            console.info(page.url + ' OK');
            page.injectJs('../public/jquery/jquery-1.10.2.min.js');
            var result = page.evaluate(pageParser);
            for (index in result.records) {
                fd.writeLine(JSON.stringify(result.records[index]));
            }
            fd.flush();
			consumeNextUrl(result.nextUrl);
        } else {
            console.error(page.url + ' FAIL connection error');
            phantom.exit(1);
        }
    } catch(exception) {
        console.error(page.url + ' FAIL ' + exception);
        phantom.exit(1);
    }
};

page.onConsoleMessage = function (msg, line, source) {
    console.log(msg);
};

page.onResourceRequested = function (requestData, request) {
	if (!new RegExp(host).test(requestData['url'])) {
		request.abort();
	}
};

consumeNextUrl(seed_path);
