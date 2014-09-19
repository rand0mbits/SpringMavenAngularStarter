if (typeof String.prototype.startsWith != 'function') {
  // see below for better implementation!
  String.prototype.startsWith = function (str){
    return this.indexOf(str) == 0;
  };
}

var util = {
	isUndefined: function (value){return typeof value === 'undefined';}, 
	isUndefinedOrNull: function(value) { return util.isUndefined(value) || value == null;},
	getAge: function (dob) {
		if (!(dob instanceof Date)) return;
		
	    var today = new Date();
	    var age = today.getFullYear() - dob.getFullYear();
	    var m = today.getMonth() - dob.getMonth();
	    if (m < 0 || (m === 0 && today.getDate() < dob.getDate())) {
	        age--;
	    }
	    return age;
	},
	
	getAgeDec: function (dob) {
		if (!(dob instanceof Date)) return;
		return (new Date().getTime() - dob.getTime()) / 1000 / 3600 / 24 / 365.2425;
	},
	
	formatHeight: function (totalInches) {
		var h = parseFloat(totalInches);
		var feet = Math.floor(h/12);
		var inches = h % 12;

		return feet + "' " + inches + '"';
	}, 
	
	getHeightCollection: function () {
		var heights = new Array();
		
		// from 5' 0" to 8' 3.5" in increments of 0.5"
		for(var i = 60; i<100; i+=0.5) {
			heights.push({ inches: i, formatted: util.formatHeight(i) });
		}
		
		return heights;
	},
	
	getPropertyFromTableField: function (field) {
		if (field == null) {
			return null;
		}
		
		field = field.toLowerCase();
		
		var index = field.indexOf("_");
		
		// Capitalize character after each underscore
		while (index != -1) {	
			if (index + 1 < field.length) {
				field = field.substr(0, index) + field.substr(index + 1, 1).toUpperCase() + field.substr(index + 2);
			}
			
			index = field.indexOf("_", index + 1);
		}
		
		field.replace("_", "");
		
		return field;
	},
	
	tmpl: function (name) {
		return 'tmpl/' + encodeURIComponent(name);
	},
	
	sum: function(arrayOfObjects, propertyToSum) {
		if (angular.isArray(arrayOfObjects)) {
			return arrayOfObjects.reduce(function(prev, curr) {
				var out = prev;
				if (angular.isArray(propertyToSum)) {
					for (var i = 0; i < propertyToSum.length; i++) {
						out += parseFloat(curr[propertyToSum[i]]) || 0;
					}
				}
				else {
					out += parseFloat(curr[propertyToSum]) || 0;
				}
				return out;
			}, 0) || 0;
		}
	},
	
	parseNumOrZero: function(num) {
		return (num - 0) || 0;
	},
	
	sortObjectArray: function(arr, compareByField) {
		if (angular.isArray(arr) && arr.length > 1) {
			arr.sort(function(a, b) {
				if (a[compareByField] < b[compareByField]){
					return -1;
				}
				if (a[compareByField] > b[compareByField]) {
					return 1;
				}	
				return 0;
			});
		}
	},
	
	daysInMonth: function(month, year) {
		if (month instanceof Date) {
			year = month.getFullYear();
			month = month.getMonth() + 1;
		}
		return new Date(year, month, 0).getDate();
	},
	
	/**
	 * Joins 2 or more variables into a string with a separator. Skips arguments that are undefined or null and omits their separators.
	 * Must have at least 3 arguments, first one being a separator of type string.
	 */
	joinWithSeparator: function() {
		if (arguments.length < 3 || !(typeof arguments[0] === 'string')) return;
		var separator = arguments[0];
		var out = '';
		for (var i = 1; i < arguments.length; i++) {
			var arg = arguments[i];
			if (util.isUndefinedOrNull(arg) || arg === '') continue;
			if (out.length > 0 && out[out.length-1] !== separator) {
				out += separator;
			}
			out += arg;
		}
		return out;
	},


    /**
     * Attach Data To Form And Submit To Specified URL
     * @param url - string
     * @param data - object {fileName:'fileName'}  include whatever other attributes are expected Server Side. Everything Will be Converted To JSON String before Sending
     * @param options -object {method:'GET | 'POST'  target:'_blank'|'_parent'  json:'true' | 'false' }    default for json attr is false
     *
     * server side method should determine the MIME type/file extension
     *
     */
	download:function(url, data, options){
        var frm=createForm(url,data,options);
        submit(frm);
        frm[0].innerHTML="";

        function createForm(url, data, options) {
            if (!options.method){
                options = $.extend({
                    'method' : 'POST'
                }, options);
            }
            if (!options.target){
                options = $.extend({
                    'target' : '_self'
                }, options);
            }

            var frm = $("form#download").attr({
                'method' : options.method,
                'action' : url,
                'target':options.target
            });

            for ( var datum in data) {
                var field = $('<input type="hidden"/>').attr({
                    'name' : (options.json)?JSON.stringify(datum):datum,
                    'value' :(options.json)? JSON.stringify(data[datum]):data[datum]
                });
                frm.append(field);
            }
            return frm;
        };

        function submit(frm) {
                window.document.forms["download"].submit(); //FF
        };
    }
};