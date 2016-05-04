var CUSTOMERS_DATA = 'api//customersDao';
var PRODUCT_DATA = 'api//productsDao';
var MARKET_DATA = 'api//marketDao';


function loadCustomers(){
	xhrGet(CUSTOMERS_DATA, function(data){
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
			addCustomers(items[i], false);

		}
	}, function(err){
		console.error(err);
	});
}

function loadProduct(){
	xhrGet(PRODUCT_DATA, function(data){
		var receivedItems = data.body || [];
		var items = [];
		var i;		
		// Make sure the received items have correct format
		console.log(receivedItems);
		for(i = 0; i < receivedItems.length; ++i){
			var item = receivedItems[i];
			if(item && 'id' in item){
				items.push(item);
			}
		}
		for(i = 0; i < items.length; ++i){
			console.log(items[i]);
			addProducts(items[i], false);

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
function addCustomers(item, isNew){
	var row = document.createElement('tr');
	var id = item && item.id;
	if(id){
		row.setAttribute('data-id', id);
	}
	row.innerHTML = "<td></td>" +
					"<td id=customerId"+id+"></td>" +
					"<td id=customerName"+id+"></td>" +
					"<td id=contactName"+id+"></td>"+
					"<td id=customerPhone"+id+"></td>"+
					"<td id=customerAddress"+id+"></td>";
//    				"<button class='deleteBtn' onclick='deleteItem(this)' title='delete me'></button>";
	var table = document.getElementById('shelterList');
	table.tBodies[0].appendChild(row);
//	table.appendChild(row);
	
	if(item){
		document.getElementById('customerId'+id).innerHTML = item.id;
		document.getElementById('customerName'+id).innerHTML = item.name;
		document.getElementById('contactName'+id).innerHTML = item.contactFirstname + " " + item.contactLastname;
		document.getElementById('customerPhone'+id).innerHTML = item.phone;
		document.getElementById('customerAddress'+id).innerHTML = item.addressLine1 + ", " + item.city + " " + item.state +
		" " + item.postalCode;
	}

	row.isNew = !item || isNew;
}

function addProducts(item, isNew){
	var row = document.createElement('tr');
	var id = item && item.id;
	if(id){
		row.setAttribute('data-id', id);
	}
	row.innerHTML = "<td></td>" +
					"<td id=productId"+id+"></td>" +
					"<td id=seafood"+id+"></td>" +
					"<td id=meat"+id+"></td>"+
					"<td id=vegetable"+id+"></td>"+
					"<td id=fruit"+id+"></td>" +
					"<td id=others"+id+"></td>" +
					"<td id=marketId"+id+"></td>" +
					"<td id=marketName"+id+"></td>" +
					"<td id=orderDate"+id+"></td>";

//    				"<button class='deleteBtn' onclick='deleteItem(this)' title='delete me'></button>";
	var table = document.getElementById('productList');
	table.tBodies[0].appendChild(row);
//	table.appendChild(row);

	
	if(item){
		document.getElementById('productId'+id).innerHTML = item.id;
		document.getElementById('seafood'+id).innerHTML = item.seafood;
		document.getElementById('meat'+id).innerHTML = item.meat;
		document.getElementById('vegetable'+id).innerHTML = item.vegetable;
		document.getElementById('fruit'+id).innerHTML = item.fruit;
		document.getElementById('others'+id).innerHTML = item.others;
		document.getElementById('orderDate'+id).innerHTML = item.orderDate;
		xhrGet(MARKET_DATA +'?id=' +item.marketId, function(data){
			document.getElementById('marketId'+id).innerHTML = data.id;
			document.getElementById('marketName'+id).innerHTML = data.name;	
		}, function(err){
			console.error(err);
		});

		

	}

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


loadCustomers();
loadProduct();
