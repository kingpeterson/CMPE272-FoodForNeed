var MARKET_DATA = 'api//marketDao';
var PRODUCT_DATA = 'api//productsDao';

function loadMarket(){
	xhrGet(MARKET_DATA, function(data){
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
			addMarket(items[i], false);

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
			if(item && 'id' in item && 'name' in item){
				items.push(item);
			}
		}
		for(i = 0; i < items.length; ++i){
			console.log(items[i]);
			addMarket(items[i], false);


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
function addMarket(item, isLast){
	var row = document.createElement('tr');
	var id = item && item.id;

	if(id){
		row.setAttribute('data-id', id);
		row.setAttribute('class', 'gradeA');
		
	}
	row.innerHTML = "<td></td>" +
					"<td id=marketId"+id+"></td>" +
					"<td id=marketName"+id+"></td>" +
					"<td id=marketPhone"+id+"></td>"+
					"<td id=marketAddress"+id+"></td>";
//    				"<button class='deleteBtn' onclick='deleteItem(this)' title='delete me'></button>";
	var table = document.getElementById('marketList');
	table.tBodies[0].appendChild(row);

//	table.appendChild(row);
	
	if(item){
		document.getElementById('marketName'+id).innerHTML = item.name;
		document.getElementById('marketPhone'+id).innerHTML = item.phone;
		document.getElementById('marketAddress'+id).innerHTML = item.addressLine1 + ", " + item.city + " " + item.state +
		" " + item.postalCode;
		document.getElementById('marketId'+id).innerHTML = item.id;

	}

//	row.isNew = !item || isNew;
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

function submitProduct(){
	var marketTemp = localStorage.getItem("marketInfo");
	var marketInfo = marketTemp.split(",");
	var seafoodValue = (document.getElementById('seafood').value == "") ? 0 : document.getElementById('seafood').value;
	var meatValue = (document.getElementById('meat').value == "") ? 0 : document.getElementById('meat').value;
	var vegetableValue = (document.getElementById('vegetable').value == "") ? 0 : document.getElementById('vegetable').value;
	var fruitValue = (document.getElementById('fruit').value == "") ? 0 : document.getElementById('fruit').value;
	var othersValue = (document.getElementById('others').value == "") ? 0 : document.getElementById('others').value;
	var orderDateValue = document.getElementById('orderDate').value;
	var orderTime = document.getElementById('orderTime').value;
	//marketTemp second element is marketId
	var id = marketInfo[1];
	var marketData;
	xhrGet(MARKET_DATA +'?id=' +id, function(data){
		console.log("data");
		console.log(data);
		if(data != null){
			marketData = data;
			console.log("marketData");
			console.log(marketData);
			
			if(seafoodValue == 0 && meatValue == 0 && vegetableValue == 0 && fruitValue == 0 && othersValue == 0){
				alert("You need to input at least one item!");
				return;
			}
			
			if(orderDateValue == "" || orderTime == ""){
				alert("Please choose the available date and time!")
				return;
			}
			console.log("I am here");
			console.log(marketData);
			console.log(marketData.latitude);
			console.log(marketData.longitude);
			
			var newProduct = {
				seafood : seafoodValue,
				meat : meatValue,
				vegetable : vegetableValue,
				fruit : fruitValue,
				others : othersValue,
				marketId : id,
				orderDate : orderDateValue + " " + orderTime,
				srcLatitude : marketData.latitude, 
				srcLongitude : marketData.longitude
			};
			console.log(newProduct);
			xhrPost(PRODUCT_DATA, newProduct, function(){
				console.log("Done updating product");
				alert("Entry submitted, thank you for your contribution!");
			}, function(err){
				console.error(err);
				alert("Updating donation failed....");
			});
		}
		else{
			alert("Market Information not found, please try again!");
			
		}
			
	});
}

loadMarket();
