var REST_DATA = 'api/database';
var REST_ENV = 'api/dbinfo';

function loadDatabase(){
	xhrGet(REST_DATA, function(data){
		document.getElementById("loading").innerHTML = "";
		var receivedItems = data.body || {};
		var items = [];
		var i;		
		// Make sure the received items have correct format
		console.log(receivedItems);
		for(i = 0; i < receivedItems.length; ++i){
			var item = receivedItems[i];
			if(item && 'id' in item && 'name' in item){
				items.push(item);
			}
		}
		for(i = 0; i < items.length; ++i){
			console.log(items[i]);
			addItem(items[i], false);
		}
		
		/*
		for(i = 0; i < receivedItems.length; ++i){
			addItem(receivedItems[i], false);
		}
		*/
	}, function(err){
		console.error(err);
		document.getElementById("loading").innerHTML = "ERROR";
	});
}

function addItem(item, isNew){
	var row = document.createElement('tr');
	var id = item && item.id;
	if(id){
		row.setAttribute('data-id', id);
	}
	row.innerHTML = "<td style='width:90%'><textarea onchange='saveChange(this)' onkeydown='onKey(event)'></textarea></td>" +
		"<td class='deleteBtn' onclick='deleteItem(this)' title='delete me'></td>";
	var table = document.getElementById('marketList');
	console.log(table.lastChild);
	table.lastChild.appendChild(row);
	var textarea = row.firstChild.firstChild;
	if(item){
		textarea.value = item.name;
	}
	row.isNew = !item || isNew;
	textarea.focus();
}

/*
function addItem(item, isNew){
	console.log(item);
	document.getElementById('marketName').innerHTML = item.toJSON().name;
	document.getElementById('marketPhone').innerHTML = item.toJSON().phone;
	document.getElementById('marketAddress').innerHTML = item.toJSON().addressLine1 + item.toJSON().addressLine2;
}
*/
function updateServiceInfo(){
	xhrGet(REST_ENV, function(dbinfo){
		
	}, function(err){
		console.error(err);
	});
}

function update(){
	document.getElementById('marketName').innerHTML = "Safeway";
	document.getElementById('marketPhone').innerHTML = "626-461-2922";
	document.getElementById('marketAddress').innerHTML = "674 Modern Ice Dr.";
}
/*
xhrGet(REST_ENV, function(dbinfo){

	console.log(dbinfo);
	document.getElementById('envServiceName').innerHTML = dbinfo.name;
	document.getElementById('envDbName').innerHTML = dbinfo.db;
	document.getElementById('envHost').innerHTML = dbinfo.host;
	document.getElementById('envPort').innerHTML = dbinfo.port;
	document.getElementById('envUrl').innerHTML = dbinfo.jdbcurl;


}, function(err){
console.error(err);
});
*/
//updateServiceInfo();
loadDatabase();
//update();