
var screenHeight=0;
var screenWidth=0;
function getScreenProperties(){
	screenHeight=screen.height;
	screenWidth=screen.width;
}

const inputs = [].slice.call(document.querySelectorAll('.controls input'));

// listen for changes
inputs.forEach(input => input.addEventListener('change', handleUpdate));
inputs.forEach(input => input.addEventListener('mousemove', handleUpdate));

function handleUpdate(e) {
    document.documentElement.style.setProperty('screenHeight', this.value);
}