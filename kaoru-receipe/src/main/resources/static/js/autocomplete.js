'use strict'
$(function () {
    $.getJSON("http://172.16.100.119:8080/receipe/searchTitle",
        function (data) {
            $('title').autocomplete({
                source: data,
                autoFocus: true,
                minLength: 1
            });
        }
    );
})