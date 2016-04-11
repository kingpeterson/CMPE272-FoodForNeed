var REST_DATA = 'api/database';
var REST_ENV = 'api/dbinfo';

function loadDatabase(){
	xhrGet(REST_DATA, function(data){
		var receivedItems = data.body || [];
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
	}, function(err){
		console.error(err);
	});
}
/*
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
*/
function addItem(item, isNew){
	var row = document.createElement('tr');
	var id = item && item.id;
	if(id){
		row.setAttribute('data-id', id);
	}
	row.innerHTML = "<td id=marketName"+id+"><strong></strong></td>" +
					"<td id=marketPhone"+id+"></td>"+
					"<td id=marketAddress"+id+"></td>";
//    				"<td class='deleteBtn' onclick='deleteItem(this)' title='delete me'></td>";
	var table = document.getElementById('marketList');
	table.appendChild(row);
	document.getElementById('marketName'+id).innerHTML = item.name;
	document.getElementById('marketPhone'+id).innerHTML = item.phone;
	document.getElementById('marketAddress'+id).innerHTML = item.addressLine1 + ", " + item.city + " " + item.state +
	" " + item.postalCode;
	
//	if(item){
		
//		document.getElementById('marketName'+id).innerHTML = item.name;
//		document.getElementById('marketPhone'+id).innerHTML = item.phone;
//		document.getElementById('marketAddress'+id).innerHTML = item.addressLine1 + ", " + item.city + " " + item.state +
//		" " + item.postalCode;
		
		/*
		var name = document.createElement('td');
		var phone = document.createElement('td');
		var address = document.createElement('td');
		row.appendChild(name);
		row.appendChild(phone);
		row.appendChild(address);
		var marketName = document.createTextNode(item.name);
		var marketPhone = document.createTextNode(item.phone);
		var marketAddress = document.createTextNode(item.addressLine1 + ", " + item.city + " " + item.state +
		" " + item.postalCode);
		name.appendChild(marketName);
		phone.appendChild(marketPhone);
		address.appendChild(marketAddress);
		*/
//	}

	row.isNew = !item || isNew;
}

function deleteItem(deleteBtnNode){
	var row = deleteBtnNode.parentNode;
	row.parentNode.removeChild(row);
	xhrDelete(REST_DATA + '?id=' + row.getAttribute('data-id'), function(){
	}, function(err){
		console.error(err);
	});
}

function onKey(evt){
	if(evt.keyCode == KEY_ENTER && !evt.shiftKey){
		evt.stopPropagation();
		evt.preventDefault();
		var row = evt.target.parentNode.parentNode;
		if(row.nextSibling){
			row.nextSibling.firstChild.firstChild.focus();
		}else{
			addItem();
		}
	}
}

function saveChange(contentNode, callback){
	var row = contentNode.parentNode.parentNode;
	var data = {
		name: contentNode.value
	};
	if(row.isNew){
		delete row.isNew;
		xhrPost(REST_DATA, data, function(item){
			row.setAttribute('data-id', item.id);
			callback && callback();
		}, function(err){
			console.error(err);
		});
	}else{
		data.id = row.getAttribute('data-id');
		xhrPut(REST_DATA, data, function(){
			console.log('updated: ', data);
		}, function(err){
			console.error(err);
		});
	}
}

function updateServiceInfo(){
	xhrGet(REST_ENV, function(dbinfo){
		
	}, function(err){
		console.error(err);
	});
}

loadDatabase();
