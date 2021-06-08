$(document).ready(function() {
    controls = $("a");
    $.each(controls, function(i, item) {
        $(item).click(function(e) {
        	var speed = $('input[name="sp"]:checked').val();
        	 var hrefs =item.getAttribute("href")+"&V="+speed;
             item.setAttribute("href",hrefs);
             return true;
        });
    });
});

