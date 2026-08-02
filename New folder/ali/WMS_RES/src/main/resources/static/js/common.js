$(document).on('keypress','.number-only', function(e) { 
		if(isNaN(this.value+""+String.fromCharCode(e.charCode)) || (e.which === 32) ) { return false; }
	})
//	.on('cut copy paste','.number-only', function(e) { 
//		e.preventDefault();
//})
;

$(document).on('keypress','.number-OnlyTwoDecimal', function(event) {
    var $this = $(this);
    if ((event.which != 46 || $this.val().indexOf('.') != -1) &&
       ((event.which < 48 || event.which > 57) &&
       (event.which != 0 && event.which != 8))) {
        event.preventDefault();
    	//return false;
    }

    var text = $(this).val();
    if ((event.which == 46) && (text.indexOf('.') == -1)) {
        setTimeout(function() {
            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
            }
        }, 1);
    }

    if ((text.indexOf('.') != -1) &&
        (text.substring(text.indexOf('.')).length > 2) &&
        (event.which != 0 && event.which != 8) &&
        ($(this)[0].selectionStart >= text.length - 2)) {
            event.preventDefault();
    }      
});

$(document).on('keypress','.number-OnlyTwoDecimalWithMinus', function(event) {
    var $this = $(this);
    if (event.which != 45 && ((event.which != 46 || $this.val().indexOf('.') != -1) &&
       ((event.which < 48 || event.which > 57) &&
       (event.which != 0 && event.which != 8)))) {
        event.preventDefault();
    	//return false;
    }

    var text = $(this).val();
    if ((event.which == 46) && (text.indexOf('.') == -1)) {
        setTimeout(function() {
            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
            }
        }, 1);
    }

    if ((text.indexOf('.') != -1) &&
        (text.substring(text.indexOf('.')).length > 2) &&
        (event.which != 0 && event.which != 8) &&
        ($(this)[0].selectionStart >= text.length - 2)) {
            event.preventDefault();
    }      
});

$(document).on('keypress','.number-WithTwoDecimal', function(e) {
	var character = String.fromCharCode(e.keyCode)
    var newValue = this.value + character;
    if (isNaN(newValue) || hasDecimalPlace(newValue, 3)) {
        e.preventDefault();
        return false;
    }
    function hasDecimalPlace(value, x) {
        var pointIndex = value.indexOf('.');
        return  pointIndex >= 0 && pointIndex < value.length - x;
    }
});

$(document).on('keypress','.digits-only', function(e) { 
	//if the letter is not digit then display error and don't type anything
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
              return false;
     }
})
.on('cut copy paste','.digits-only', function(e) { 
	//e.preventDefault();
	/*var val = $(this).val();  
    if (val!='0'){  
         val=val.replace(/[^0-9]+/g, "");
         $(this).val(val);  
    }  */
});

$(document).on('keypress','.no-space-start', function(e) { 
	if(e.which == 32 &&  e.target.selectionStart == 0) 
        return false;
})
//.on('cut copy paste','.no-space-start', function(e) { 
//	e.preventDefault();
//})
;

$(document).on('keypress','.form-control', function(e) { 
	if((e.which == 32 || e.which == 44 || e.which == 91 || e.which == 92 || e.which == 123 || e.which == 124 || e.which == 93 || e.which == 125 || e.which == 58 || e.which == 59 || e.which == 39 || e.which == 34 || e.which == 63 || e.which == 33 || e.which == 35 || e.which == 126 || e.which == 46 || e.which == 47 || e.which==94 || e.which==95 || e.which==96 || e.which==60 || e.which==61 || e.which==43 || e.which==64 || e.which==62 || e.which==36 || e.which==37 || e.which==38 || e.which==42 || e.which==40 || e.which==41 || e.which==45) &&  e.target.selectionStart == 0) 
        return false;
});

$(document).on('keypress','.no-space', function(e) { 
	if(e.which === 32) 
        return false;
})
//.on('cut copy paste','.no-space', function(e) { 
//	e.preventDefault();
//})
;

$(document).on('keypress','.alpha-numeric-only', function(e) { 
	if (e.which < 48 || 
		    (e.which > 57 && e.which < 65) || 
		    (e.which > 90 && e.which < 97) ||
		    e.which > 122) {
		   return false;
		}
})
//.on('cut copy paste','.alpha-numeric-only', function(e) { 
//	e.preventDefault();
//})
;


$(document).on('keypress','.number6', function(e) { 
	 if (this.value.length >= 6) {
	        return false;
	    }
});

$(document).on('keypress','.number10', function(e) { 
	 if (this.value.length >= 10) {
	        return false;
	    }
});

$(document).on('keypress','.number12', function(e) { 
	 if (this.value.length >= 12) {
	        return false;
	    }
});

$(document).on('keypress','.alpha-only-space-dot', function(event) { 
	 var inputValue = event.which;
     // allow letters and whitespaces only.
     if((!(inputValue >= 65 && inputValue <= 120) && (inputValue != 32 && inputValue != 0 && inputValue != 46))) { 
         event.preventDefault(); 
     }
})
//.on('cut copy paste','.alpha-only-space-dot', function(e) { 
//	e.preventDefault();
//})
;

$(document).on('keypress','.alpha-only', function(e) { 
	if (e.which < 65 || 
		    (e.which > 90 && e.which < 97) ||
		    e.which > 122) {
		   return false;
		}
});

$(document).on('keypress','.alpha-numeric-with-hyphen-slash', function(e) { 
	if (!(e.which >= 48 && e.which <= 57) && !(e.which >= 65 && e.which <= 90) && !(e.which >= 97 && e.which <= 122) && (e.which != 45 && e.which != 47)) {
		   return false;
		}
});

$(document).on('keypress','.numeric-with-hyphen', function(e) { 
	if (!(e.which >= 48 && e.which <= 57) && (e.which != 45)) {
		   return false;
		}
});

$(document).on('keypress','.alpha-numeric-with-dot', function(e) { 
	if (!(e.which >= 48 && e.which <= 57) && !(e.which >= 65 && e.which <= 90) && !(e.which >= 97 && e.which <= 122) && (e.which != 46)) {
		   return false;
		}
});



$(document).on('keypress','.number-OnlyThreeDecimal', function(event) {
    var $this = $(this);
    if ((event.which != 46 || $this.val().indexOf('.') != -1) &&
       ((event.which < 48 || event.which > 57) &&
       (event.which != 0 && event.which != 8))) {
        event.preventDefault();
    	//return false;
    }

    var text = $(this).val();
    if ((event.which == 46) && (text.indexOf('.') == -1)) {
        setTimeout(function() {
            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
            }
        }, 1);
    }

    if ((text.indexOf('.') != -1) &&
        (text.substring(text.indexOf('.')).length > 3) &&
        (event.which != 0 && event.which != 8) &&
        ($(this)[0].selectionStart >= text.length - 3)) {
            event.preventDefault();
    }      
});

