$(document).ready(function(){
	
	/*<![CDATA[*/
	 var lang = $('#lang').val();
	 //alert(lang);
	 var url = window.location.href;
	 //alert(url);
	 var paramSplit=url.split('?');
	 
	 if(null==paramSplit[1]){
		    var arr = url.split('#');
	    	if(lang=='en'){
	    		$('#hiLocale').css("display", "");
	    		$('#enLocale').css("display", "none");
	    		$("#hiLocale").attr("href", arr[0]+'?lang=hi'+'#'+arr[1]);
	    		$("#hiLocale").attr("onclick", "return confirm('Any details filled in form will be lost. Are you sure to change language?')");
	    	}else if(lang=='hi'){
	    		$('#hiLocale').css("display", "none");
	    		$('#enLocale').css("display", "");
	    		$("#enLocale").attr("href", arr[0]+'?lang=en'+'#'+arr[1]);
	    		$("#enLocale").attr("onclick", "return confirm('कोई भी फॉर्म मैं भरी हुई डिटेल्स लॉस्ट हो जायेगी| क्या आप सुनिश्चित है भाषा बदलने के लिए?')");
	    	}
	 } else {
		 var arr2 = paramSplit[1].split('#');
		 if(lang=='en'){
			$('#hiLocale').css("display", "");
			$('#enLocale').css("display", "none");
			$("#hiLocale").attr("href", paramSplit[0]+'?lang=hi'+'#'+arr2[1]);
			$("#hiLocale").attr("onclick", "return confirm('Any details filled in form will be lost. Are you sure to change language?')");
		}else if(lang=='hi'){
			$('#hiLocale').css("display", "none");
			$('#enLocale').css("display", "");
			$("#enLocale").attr("href", paramSplit[0]+'?lang=en'+'#'+arr2[1]);
			$("#enLocale").attr("onclick", "return confirm('कोई भी फॉर्म मैं भरी हुई डिटेल्स लॉस्ट हो जायेगी| क्या आप सुनिश्चित है भाषा बदलने के लिए?')");
		}
	}
	/*]]>*/
});