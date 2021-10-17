let canvas = document.getElementById("graph-canvas");
let ctx = canvas.getContext('2d');

ctx.lineWidth = 2;
ctx.beginPath();
ctx.moveTo(0, 250);
ctx.lineTo(500, 250);
ctx.moveTo(250, 500);
ctx.lineTo(250, 0);

ctx.moveTo(50, 253);
ctx.lineTo(50, 247);
ctx.moveTo(150, 253);
ctx.lineTo(150, 247);
ctx.moveTo(350, 253);
ctx.lineTo(350, 247);
ctx.moveTo(450, 253);
ctx.lineTo(450, 247);

ctx.font = "12px Verdana";
ctx.textAlign = "start";
ctx.fillText("-R", 43, 245);
ctx.fillText("-R/2", 138, 245);
ctx.fillText("R/2", 340, 245);
ctx.fillText("R", 445, 245);


ctx.moveTo(247, 50);
ctx.lineTo(253, 50);
ctx.moveTo(247, 150);
ctx.lineTo(253, 150);
ctx.moveTo(247, 350);
ctx.lineTo(253, 350);
ctx.moveTo(247, 450);
ctx.lineTo(253, 450);

ctx.font = "12px Verdana";
ctx.textAlign = "left";
ctx.fillText("-R", 255, 453);
ctx.fillText("-R/2", 255, 353);
ctx.fillText("R/2", 255, 153);
ctx.fillText("R", 255, 55);

ctx.lineWidth = 1;
ctx.moveTo(246, 7);
ctx.lineTo(250, 0);
ctx.lineTo(254, 7);
ctx.moveTo(493, 246);
ctx.lineTo(500, 250);
ctx.lineTo(493, 254);
ctx.stroke();

ctx.fillStyle = "rgba(0, 0, 255, 0.5)";
ctx.moveTo(250, 150);
ctx.lineTo(150, 250);
ctx.lineTo(250, 250);
ctx.lineTo(250, 150);
ctx.rect(250, 150, 200, 100);
ctx.arc(250, 250, 200, 0, Math.PI/2, false);
ctx.fill();



let x, y, r;
let graphUse = false;



function getCheckboxCount(name){
    let rad = document.getElementsByName(name.toLowerCase());
    let counter = 0;
    for (let i=0;i<rad.length; i++) {
        if (rad[i].checked){
            counter++;
            if(name === "X")
                x = rad[i].getAttribute("value");
            if(name === "R")
                r = rad[i].getAttribute("value");
        }
    }
    return counter;
}

function clearCheckboxes(elementName){
    let rad=document.getElementsByName(elementName.toLowerCase());
    for (let i=0;i<rad.length; i++) {
        if (rad[i].checked)
            rad[i].checked = false;
    }
}

function showError(message) {
    $('#errors').append('<li>'+ message + '</li>');
}

function reset(){

    clearCheckboxes("X")

    document.getElementById("y-textinput").value = "";

    clearCheckboxes("R")
}

function submit() {
    $('#errors').empty();
    if (validateData()) {
       sendRequest();
    }
}

function sendRequest(){
    $('#errors').empty();
    return  $.ajax({
        url: "controller",
        method: "POST",
        data: {
            'x': x,
            'y': y,
            'r': r,
            'graphUse': graphUse
        },
        success: function (data) {
            $('#result-table tr:first').after(data);
            return true;
        }
    });
    return false;
}


function validateCheckboxValue(argumentName){
    let chosenCheckboxes = getCheckboxCount(argumentName);
    if(chosenCheckboxes === 1){
        return true;
    }
    if(chosenCheckboxes === 0){
        showError("Выберите " + argumentName);
    }
    if(chosenCheckboxes > 1){
        showError("Выберите только одно значение " + argumentName);
    }
    return false;
}


function validateX() {
    return validateCheckboxValue("X");
}

function validateY() {
    y = document.querySelector("input[id=y-textinput]").value.replace(",", ".");
    if (!y){
        showError("Введите Y");
        return false;
    }
    if (isNaN(parseFloat(y))) {
        showError("Y не является числом");
        return false;
    }
    if (!((y > -5) && (y < 5))) {
        showError("Y не входит в область допустимых значений");
        return false;
    }
    return true;
}

function validateR() {
    return validateCheckboxValue("R");
}

function validateData() {
        return validateX() & validateY() & validateR();
}


let  ctx1 = canvas.getContext('2d');
function drawPoint(x, y) {
    ctx1.beginPath();
    ctx1.fillStyle = 'red';
    ctx1.arc(x, y, 2, 0, 2 * Math.PI);
    ctx1.fill();
}

$('#graph-canvas').on("click", function (event) {
    if (validateR()) {
        let offX = event.offsetX;
        let offY = event.offsetY;
        //console.log(offX, offY);
        x = (offX - 250) * (r / 200);
        y = - (offY - 250) * (r / 200);
        //console.log(x, y);
        graphUse = true;
        if (sendRequest()) {
             drawPoint(offX, offY);
        }
        graphUse = false;
    }

    // else {
    //     alert("R not chosen.")
    // }
})