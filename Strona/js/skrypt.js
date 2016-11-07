
var screenHeight;
var screenWidth;
function getScreenProperties(){
	screenHeight=screen.height;
	screenWidth=screen.width;
	document.getElementById("ankieta_container").innerHTML = screen.width;
	handleUpdate();
}

//const inputs = [].slice.call(document.querySelectorAll('.controls input'));

// listen for changes
//inputs.forEach(input => input.addEventListener('change', handleUpdate));
//inputs.forEach(input => input.addEventListener('mousemove', handleUpdate));

function handleUpdate(val) {
    document.documentElement.style.setProperty('screenHeight', val);
}