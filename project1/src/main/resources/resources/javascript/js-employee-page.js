// console.log("hello s");


let sortDirection = false;
 
// // reimbId, reimbAmmount, reimbSubmitted, reimbResolved, reimbDescription, reimbAuthor, reimbResolver,
// reimbStatus, reimbType -->



window.onload = function()
{
	getUserData();
    addSortingToColumnHeader();
    getTicketData();
    
}


async function getTicketData()
{
	//const tableBody = document.getElementById(`tableData`);
	
	//console.log("inside get ticket data");
	
	const responsePayload = await fetch(`http://localhost:9001/api/user/employee/reimbursementTickets`);
	
	//console.log(responsePayload);
	
	const ourJSON = await responsePayload.json();
	
	//console.log(ourJSON);
	loadTableData(ourJSON);
}

async function getUserData()
{
	const responsePayload =  await fetch(`http://localhost:9001/api/user/info`,{method : `POST`});

		
	
	const ourJSON = await responsePayload.json();;
	
	loadUserInfo(ourJSON);
}

async function logout()
{
    const responsePayload =  await fetch(`http://localhost:9001/logout`,{method : `POST`});
    window.location.reload();
}


let loadUserInfo = function(userInfo)
{
	//manipulate the DOM to add info about the current user
	
	let name = document.getElementById("userName");
	
	name.innerText = `Hello, ${userInfo.firstName.charAt(0).toUpperCase() + userInfo.firstName.slice(1)} ${userInfo.lastName.charAt(0).toUpperCase() + userInfo.lastName.slice(1)}`;
	
    console.log(`Hello, ${userInfo.firstName.charAt(0).toUpperCase() + userInfo.firstName.slice(1)} ${userInfo.lastName.charAt(0).toUpperCase() + userInfo.lastName.slice(1)}`);
	
	//console.log(userInfo);
}


//purpose is to inject the data to DOM dynamic
let loadTableData = function(ticketData)
{
    const tableBody = document.getElementById(`tableData`);
    let dataHTML = ``;

  	for(let ticket in ticketData)
    {
        let submittedDate = ticketData[ticket].reimbSubmitted.split(" ");

        dataHTML += `<tr><td>${ticketData[ticket].reimbId}</td>
        				<td>$${ticketData[ticket].reimbAmount}</td>
        				<td>${submittedDate[0]}</td>
                        <td>${(ticketData[ticket].reimbResolved == null)? "unresolved" : ticketData[ticket].reimbResolved.split(" ")[0]}</td>
                        <td>${ticketData[ticket].reimbDescription}</td>
                        <td>${ticketData[ticket].reimbStatus}</td>
                        <td>${ticketData[ticket].reimbType}</td></tr>`;
                        
		/*dataHTML += `<tr><td>${ticketData[ticket].reimbId}</td>
        				<td>$${ticketData[ticket].reimbAmount}</td>
        				<td>${ticketData[ticket].reimbSubmitted}</td>
                        <td>${ticketData[ticket].reimbResolved}</td>
                        <td>${ticketData[ticket].reimbDescription}</td>
                        <td>${ticketData[ticket].reimbAuthor}</td>
                        <td>${ticketData[ticket].reimbResolver}</td>
                        <td>${ticketData[ticket].reimbStatus}</td>
                        <td>${ticketData[ticket].reimbType}</td></tr>`;*/
                        
    }
    tableBody.innerHTML = dataHTML;
    // console.log(dataHTML);
    
    /*for(let ticket in ticketData)
    {
		console.log(ticketData[ticket].reimbAmount);
	}*/
}



/**
 *  Sorts a html table
 * 
 * @param {HTMLTableElement} table the table to sort
 * @param {number} column the index of the column to sort
 * @param {boolean} asc determines if the sorting will be ascending 
 */
let sortTableByColumn = function(table, column, asc = true) {
    const dirModifier = asc ? 1 : -1;
    const tBody = table.tBodies[0];
    const rows = Array.from(tBody.querySelectorAll("tr"));

    //add a if else in here to make sure we dont try and sort on a number
    // 0, 1, 3 are numbers do not sort them with below code

    // Sort each row
    const sortedRows = rows.sort((a, b) => {
        const aColText = a.querySelector(`td:nth-child(${ column + 1 })`).textContent.trim().replace("-", "").replace("-", "");
        const bColText = b.querySelector(`td:nth-child(${ column + 1 })`).textContent.trim().replace("-", "").replace("-", "");
        console.log(aColText);
        return aColText > bColText ? (1 * dirModifier) : (-1 * dirModifier);
    });

    // Remove all existing TRs from the table
    while (tBody.firstChild) {
        tBody.removeChild(tBody.firstChild);
    }

    // Re-add the newly sorted rows
    tBody.append(...sortedRows);

    // Remember how the column is currently sorted
    table.querySelectorAll("th").forEach(th => th.classList.remove("th-sort-asc", "th-sort-desc"));
    table.querySelector(`th:nth-child(${ column + 1})`).classList.toggle("th-sort-asc", asc);
    table.querySelector(`th:nth-child(${ column + 1})`).classList.toggle("th-sort-desc", !asc);
}


let addSortingToColumnHeader = function()
{
	document.querySelectorAll(".table-sortable th").forEach(headerCell => {
    	headerCell.addEventListener("click", () => {
        	const tableElement = headerCell.parentElement.parentElement.parentElement;
        	const headerIndex = Array.prototype.indexOf.call(headerCell.parentElement.children, headerCell);
        	const currentIsAscending = headerCell.classList.contains("th-sort-asc");

        	sortTableByColumn(tableElement, headerIndex, !currentIsAscending);
    	});
	});
}


const openNewReimbursementTicketModal = function() //open the modal that allows the user to enter input.... clear all previous input
{
    const modal = document.getElementById('modal');
    // const overlay = document.getElementById('overlay');
    document.getElementById("amount").style.backgroundColor = 'white';
    document.getElementById("amount").value = "";
    document.getElementById("description").value = "";
    document.getElementById('0').checked = false;
    document.getElementById('1').checked = false;
    document.getElementById('2').checked = false;
    document.getElementById('3').checked = false;
    openModal(modal);

    
}



let submitNewTicket = function()
{
    const modal = document.getElementById('modal');
    //const overlay = document.getElementById('overlay');
    

    // console.log(typeof(document.getElementById("amount").value + " amount"));
    // console.log(document.getElementById("description").value  + " decription");
    let amount = document.getElementById("amount").value;
    let description = (document.getElementById("description").value)? document.getElementById("description").value: `empty`;
    let type; //type of the reimbursement ticket variable

    if(document.getElementById("0").checked)
    {
        type = 1;
        //console.log("Food");
    }
    else if(document.getElementById("1").checked)
    {
        type = 2;
        //console.log("Travel");
    }
    else if(document.getElementById("2").checked)
    {
        type = 3;
        //console.log("Lodgeing");
    }
    else if(document.getElementById("3").checked)
    {
        type = 0;
        //console.log("Other");
    }

    if(validateDataAmount(document.getElementById("amount").value) & valideateDataRadioButtonSelected())
    {
        let myTicket = buildMyObj(parseFloat(amount), description, type);
        //console.log(JSON.stringify(myTicket));
        
        //move this to its own function
        fetch(`http://localhost:9001/api/user/employee/reimbursementTickets/newTicketCreation`, {
		
		method: `post`,
		body : JSON.stringify(myTicket),
		headers: {
			'Content-Type' : `application/json`
			}
		});
        
        
        closeModal(modal);
        
        updateTicketTable();
        
    }
    else
    {
        //do nothing input was not correct......:(
    }
}

let valideateDataRadioButtonSelected = function()
{
    if(document.getElementById("0").checked)
    {
        return true;
    }
    else if(document.getElementById("1").checked)
    {
        return true;
    }
    else if(document.getElementById("2").checked)
    {
        return true;
    }
    else if(document.getElementById("3").checked)
    {
        return true;
    }
    else
    {
        return false;
    }
}

let validateDataAmount = function(amount) //validates the amount data input to be precisely an numerica value with/ without a decimal to two places
{
    //console.log(amount.length == 0);
    //console.log(isNaN(amount));
    if(amount)
    {
        if(isNaN(amount))
        {
            //console.log("Entered value was NOt an int ");
            document.getElementById("amount").style.backgroundColor = 'red';
            return false;
        }
        else{
            let checkDecimal = amount.split('.');
            //console.log(checkDecimal);

            if(checkDecimal.length == 2)
            {
                if(checkDecimal[1].length == 2)
                {
                    //console.log("Entered value was an int and it did have a decimal place.... all good");
                    return true;
                }
                else
                {
                    document.getElementById("amount").style.backgroundColor = 'red';
                }
            }
            else
            {
                //console.log("Enter value was an int but did not have a decimal place... all good");
                return true;
            }
        }
    }
    else
    {
        document.getElementById("amount").style.backgroundColor = 'red';
        return false;
        //console.log("input was empty")
    }
}

function openModal(modal)
{
    if(modal == null)
    {
        return
    }

    modal.classList.add('active');
    overlay.classList.add('active');
}


function closeModal(modal)
{
    if(modal == null)
    {
        return
    }

    modal.classList.remove('active');
    overlay.classList.remove('active');
}

function closeOpenModal()
{
    const modal  = document.getElementById(`modal`);

    closeModal(modal);
}
let buildMyObj = function (amount, descripton, type)
{
    // //int reimbAmount, String reimbDescription, int reimbAuthor, int reimbStatusId, int reimbTypeId
    return myObj = {reimbAmount: amount, reimbDescription: descripton, reimbTypeId: type};
}


let updateTicketTable = function()//has to be a better way than just refreshing the entire page
{
	window.location.reload();
}































