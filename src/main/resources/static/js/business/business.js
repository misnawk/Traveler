function binWrite(){
    let binCate = document.getElementById("binCate").value;

    // Correctly log the binCate value to the console
    console.log(binCate);

    if(binCate == "1"){
        location.href="/binpage/airline";
    } else if(binCate == "2"){
        location.href="/binpage/hotel";
    } else if(binCate == "3"){
        location.href="/binpage/tick";
    } else if(binCate == "4"){
        location.href="/binpage/package";
    }
}
