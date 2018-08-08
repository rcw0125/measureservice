/**
 * @author jianglu<jianglu0533@163.com>
*/

!function ($) {
	var there;
    var thererow;
    var thererowindex;
    var therecolumns;
    var thereoldvalues = new Array();
    var thereurl;
    var theretableid;
    var theretakebackfields;
    'use strict';
    $.extend($.fn.bootstrapTable.defaults, {
        popupedit: true,
        onPopupeditInit: function () {
            return false;
        },
        onPopupeditSave: function (field, row, oldValue, $el) {
            return false;
        },
        onPopupeditShown: function (field, row, $el, popupedit) {
            return false;
        },
        onPopupeditHidden: function (field, row, $el, reason) {
            return false;
        }
    });

    $.extend($.fn.bootstrapTable.Constructor.EVENTS, {
        'popupedit-init.bs.table': 'onPopupeditInit',
        'popupedit-save.bs.table': 'onPopupeditSave',
        'popupedit-shown.bs.table': 'onPopupeditShown',
        'popupedit-hidden.bs.table': 'onPopupeditHidden'
    });

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _initTable = BootstrapTable.prototype.initTable,
        _initBody = BootstrapTable.prototype.initBody;

    BootstrapTable.prototype.initTable = function () {
        var that = this;
        _initTable.apply(this, Array.prototype.slice.apply(arguments));

        if (!this.options.popupedit) {
        	return;
        }

        $.each(this.columns, function (i, column) {
            if (!column.popupedit) {
                return;
            }
            
            var _formatter = column.formatter;
            column.formatter = function (value, row, index) {
                var result = _formatter ? _formatter(value, row, index) : value;
                return ['<a href="javascript:void(0)"',
                        ' data-name="' + column.field + '"',
                        ' data-index="' + index + '"',
                        ' class="popupedit-click"',
                    '>' + value + '</a>'
                ].join('');
            };
        });
    };

    BootstrapTable.prototype.initBody = function () {
        var that = this;
        var thatdata = that.getData();
        _initBody.apply(this, Array.prototype.slice.apply(arguments));

        if (!this.options.popupedit) {
            return;
        }
        
        $.each(this.columns, function (i, column) {
            if (!column.popupedit) {
                return;
            }
            
            var popupeditOptions = {},popupeditDataPrefix = 'popupedit-';
            var processDataOptions = function(key, value) {
            var dashKey = key.replace(/([A-Z])/g, function($1){return "-"+$1.toLowerCase();});
            	if (dashKey.slice(0, popupeditDataPrefix.length) == popupeditDataPrefix) {
                    var dataKey = dashKey.replace(popupeditDataPrefix, '');
                    popupeditOptions[dataKey] = value;
            	}
            };
            $.each(that.options,processDataOptions);
            $.each(column, processDataOptions);
            that.$body.find('a[data-name="' + column.field + '"]').click(function(){
            	there = $(this);
            	thererowindex = there.parents('tr[data-index]').data('index');
                thererow = thatdata[thererowindex];
                therecolumns = popupeditOptions['localcolumns'];
                var therecolumnsArry = therecolumns.split(",");
                for(var i=0;i<therecolumnsArry.length;i++){
                	thereoldvalues[i] = thererow[therecolumnsArry[i]];
                }
                thereurl = popupeditOptions['url'];
                theretableid = popupeditOptions['tableid'];
                theretakebackfields = popupeditOptions['takebackfields'];
            	$('#GridPopupWindow').modal('show');
            });
        });
        
        $('#GridPopupWindow').off('hide.bs.modal').on('hide.bs.modal', function(){
        	var dgm = window.frames["popupframe"].dataGridsMap;
        	if(undefined != dgm){
        		var gridPopupSelectionsData = dgm.get(theretableid).bootstrapTable('getAllSelections');
        		if(1 == gridPopupSelectionsData.length){
        			var theretakebackfieldsArry = theretakebackfields.split(",");
        			var therecolumnsArry = therecolumns.split(",");
        			for(var i=0;i<theretakebackfieldsArry.length;i++){
        				var newValue = gridPopupSelectionsData[0][theretakebackfieldsArry[i]];
            			thererow[therecolumnsArry[i]] = newValue;
        			}
        			$('#' + that.$el.attr('id')).bootstrapTable('updateRow',{index:thererowindex,row:thererow});
        			that.trigger('popupedit-save',therecolumns,thererow,thereoldvalues,there);
        		}else{
        			warningbox('请选择一条数据操作！');
        		}
        	}
       	});
    	$('#GridPopupWindow').off('shown.bs.modal').on('shown.bs.modal', function(){
    		var canLoadContent = true;
    		var thereurlArry = thereurl.split('${');
    		for(var i=0;i<thereurlArry.length;i++){
    			var functionendIndex = thereurlArry[i].indexOf('}');
    			if(functionendIndex > -1){
    				var functionName = thereurlArry[i].substring(0,functionendIndex);
    				var up = dynamicall(functionName);
    				if(false != up){
    					thereurl = thereurl.replace('${'+functionName+'}',up);
    				}else{
    					canLoadContent = false;
    					errorbox(functionName + '参数设置错误，请返回检查！');
    					$('#GridPopupWindow').modal('toggle');
    					break;
    				}
    			}
    		}
    		if(canLoadContent){
    			window.frames["popupframe"].location.href = thereurl;
    		}
    	});
        this.trigger('popupedit-init');
	};
}(jQuery);
