/**
 * @author zhixin wen <wenzhixin2010@gmail.com>
 * extensions: https://github.com/vitalets/x-editable
 */

!function ($) {

    'use strict';

    $.extend($.fn.bootstrapTable.defaults, {
        editable: true,
        onEditableInit: function () {
            return false;
        },
        onEditableSave: function (field, row, oldValue, $el) {
            return false;
        },
        onEditableShown: function (field, row, $el, editable) {
            return false;
        },
        onEditableHidden: function (field, row, $el, reason) {
            return false;
        }
    });

    $.extend($.fn.bootstrapTable.Constructor.EVENTS, {
        'editable-init.bs.table': 'onEditableInit',
        'editable-save.bs.table': 'onEditableSave',
        'editable-shown.bs.table': 'onEditableShown',
        'editable-hidden.bs.table': 'onEditableHidden'
    });

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _initTable = BootstrapTable.prototype.initTable,
        _initBody = BootstrapTable.prototype.initBody;

    BootstrapTable.prototype.initTable = function () {
        var that = this;
        _initTable.apply(this, Array.prototype.slice.apply(arguments));

        if (!this.options.editable) {
            return;
        }
        
        $.each(this.columns, function (i, column) {
            if (!column.editable) {
                return;
            }
            var editableOptions = {}, editableDataMarkup = [], editableDataPrefix = 'editable-';

            var processDataOptions = function(key, value) {
              var dashKey = key.replace(/([A-Z])/g, function($1){return "-"+$1.toLowerCase();});
              if (dashKey.slice(0, editableDataPrefix.length) == editableDataPrefix) {
                var dataKey = dashKey.replace(editableDataPrefix, 'data-');
                editableOptions[dataKey] = value;
              }
            };

            $.each(that.options, processDataOptions);

            var _formatter = column.formatter;
            column.formatter = function (value, row, index) {
                var result = _formatter ? _formatter(value, row, index) : value;

                $.each(column, processDataOptions);
                if('combobox'== column.edittype){
                	var require_val = '';
                	if(column.require){
                		require_val = 'data-bv-notempty data-bv-notempty-message="请填写" ' + column.title + ' "!"';
                	}
                	return ['<form id="' + that.$el[0].id + column.field + index + 'form" onsubmit="return false;"><div class="input-group input-group-sm" style="width:100%;">',
                            '<select id="' + that.$el[0].id + column.field + index + '" name="' + that.$el[0].id + column.field + index + '" class="form-control select2 select2-data-ajax" ' + require_val + ' style="border-radius:0px;" value="'+value+'" index="'+index+'"></select>',
                            '</div></form>'
                    ].join('');
                }else if('datetime'== column.edittype){
                	var require_val = '';
                	if(column.require){
                		require_val = 'data-bv-notempty data-bv-notempty-message="请填写" ' + column.title + ' "!"';
                	}
                	var value_val = '';
                	if(value != ''){
                		value_val = 'value = "' + value + '"';
                	}
                	return ['<form id="' + that.$el[0].id + column.field + index + 'form" onsubmit="return false;"><div class="input-group input-group-sm date" style="width:100%;">',
                            '<input type="text" class="form-control" placeholder="' + column.title + '" id="' + that.$el[0].id + column.field + index + '" name="' + that.$el[0].id + column.field + index + '" ' + require_val + ' style="border-radius:0px;" ' + value_val + ' index="'+index+'"/>',
                            '</div></form>'
                    ].join('');
                }else if('integerbox'== column.edittype){
                	var require_val = '';
                	if(column.require){
                		require_val = 'data-bv-notempty data-bv-notempty-message="请填写" ' + column.title + ' "!"';
                	}
                	return ['<form id="' + that.$el[0].id + column.field + index + 'form" onsubmit="return false;"><div class="input-group input-group-sm" style="width:100%;">',
                            '<input type="text" class="form-control" placeholder="' + column.title + '" id="' + that.$el[0].id + column.field + index + '" name="' + that.$el[0].id + column.field + index + '" data-bv-integer data-bv-integer-message="' + column.title + '必须填写整数!" ' + require_val + ' style="border-radius:0px;" value="'+value+'" index="'+index+'"/>',
                            '</div></form>'
                    ].join('');
                }else if('floatbox'== column.edittype){
                	var require_val = '';
                	if(column.require){
                		require_val = 'data-bv-notempty data-bv-notempty-message="请填写" ' + column.title + ' "!"';
                	}
                	return ['<form id="' + that.$el[0].id + column.field + index + 'form" onsubmit="return false;"><div class="input-group input-group-sm" style="width:100%;">',
                            '<input type="text" class="form-control" placeholder="' + column.title + '" id="' + that.$el[0].id + column.field + index + '" name="' + that.$el[0].id + column.field + index + '" data-bv-numeric data-bv-numeric-message="' + column.title + '必须填写数字!" ' + require_val + ' style="border-radius:0px;" value="'+value+'" index="'+index+'"/>',
                            '</div></form>'
                    ].join('');
                }else if('popupbox'== column.edittype){
                	var require_val = '';
                	if(column.require){
                		require_val = 'data-bv-notempty data-bv-notempty-message="请选择" ' + column.title + ' "!"';
                	}
                	return ['<form id="' + that.$el[0].id + column.field + index + 'form" onsubmit="return false;"><div class="input-group input-group-sm" style="width:100%;">',
                            '<input type="text" class="form-control" placeholder="' + column.title + '" id="' + that.$el[0].id + column.field + index + '" name="' + that.$el[0].id + column.field + index + ' style="border-radius:0px;" value="'+value+'" index="'+index+'"/>',
                            '</div></form>'
                    ].join('');
                }else{
                	var require_val = '';
                	if(column.require){
                		require_val = 'data-bv-notempty';
                	}
                	return ['<form id="' + that.$el[0].id + column.field + index + 'form" onsubmit="return false;"><div class="input-group input-group-sm" style="width:100%;">',
                            '<input type="text" class="form-control" placeholder="' + column.title + '" id="' + that.$el[0].id + column.field + index + '" name="' + that.$el[0].id + column.field + index + '" ' + require_val + ' style="border-radius:0px;" value="'+value+'" index="'+index+'"/>',
                            '</div></form>'
                    ].join('');
                }
            };
        });
    };

    BootstrapTable.prototype.initBody = function () {
        var that = this;
        _initBody.apply(this, Array.prototype.slice.apply(arguments));

        if (!this.options.editable) {
            return;
        }
        var rowcount = $('#' + that.$el[0].id + ' tbody tr').length;
        for(var rowindex=0;rowindex<rowcount;rowindex++){
        	$.each(this.columns, function (i, column) {
                if (!column.editable) {
                    return;
                }
                var editingTd = $('#' + that.$el[0].id + column.field + rowindex + 'form').parent();
                if(!editingTd.hasClass('bootstrapTableEditable')){
                	editingTd.addClass('bootstrapTableEditable');
                }
                if('combobox'== column.edittype){
                	if(undefined != column.combodata){
                		var select2Data;
                		if(typeof column.combodata == 'object'){
                			select2Data = column.combodata;
                		}else{
                			select2Data = JSON.parse(column.combodata);
                		}
                		$("#" + that.$el[0].id + column.field + rowindex).select2({
                			theme:"bootstrap",
                			allowClear:true,
                			placeholder:"请选择",
                			data:select2Data
            			});
                	}else{
                		initcombobox(that.$el[0].id + column.field + rowindex,column.url,column.title,true);
                		$('#' + that.$el[0].id + column.field + rowindex).trigger('change',{term:$('#' + that.$el[0].id + column.field + rowindex).attr('value'),setvalue:true,remote:true}).val($('#' + that.$el[0].id + column.field + rowindex).attr('value'));
                	}
                	
                	validForm(that.$el[0].id + column.field + 'form');
                	$('#' + that.$el[0].id + column.field + rowindex).change(function(){
                		var data = that.getData();
                        var index = $(this).parents('tr[data-index]').data('index');
                        var row = data[index];
                        var oldValue = row[column.field];
                        if(undefined == column.usetextvalue || !column.usetextvalue){
                        	row[column.field] = $(this).val();
                        }else{
                        	row[column.field] = $(this).find("option:selected").text()
                        }
                        
                        validForm(that.$el[0].id + column.field + 'form');
                	});
                }else if('datetime'== column.edittype){
                	$('.fixed-table-body').css('overflow-x','inherit').css('overflow-y','inherit');
                	$('#' + that.$el[0].id + column.field + rowindex).datetimepicker({
    					format : column.format,
    					locale : 'zh-cn'
    				});
                	$('#' + that.$el[0].id + column.field + rowindex).off('dp.change').on("dp.change", function(e) {
                		var data = that.getData();
                        var index = $(this).parents('tr[data-index]').data('index');
                        var row = data[index];
                        var oldValue = row[column.field];
                        row[column.field] = e.date.format(column.format);//YYYY-MM-DD HH:mm:ss
                        validForm(that.$el[0].id + column.field + 'form');
    				});
                }else if('popupbox'== column.edittype){
                	$("#" + that.$el[0].id + column.field + rowindex).off('click').on('click',function(){
                		var oo = $(this);
                		$('#' + column.popupwinid).off('hide.bs.modal').on('hide.bs.modal', function (e) {
                    		var selectedrow = $('#' + column.datagridid).bootstrapTable('getAllSelections');
                    		if(1 == selectedrow.length){
                    			var data = that.getData();
                                var index = oo.parents('tr[data-index]').data('index');
                                var row = data[index];
                                
                    			var fromfields = column.fromfields.split(',');
                    			var tofields = column.tofields.split(',');
                    			for(var f=0;f<fromfields.length;f++){
                    				row[tofields[f]] = selectedrow[0][fromfields[f]];
                    				try
                    				{
                    					$("#" + that.$el[0].id + tofields[f] + index).val(selectedrow[0][fromfields[f]]);
                    				}
                    				catch(e)
                    				{
                    					
                    				}
                    			}
                    			validForm(that.$el[0].id + column.field + 'form');
                    		}else{
                    			errorbox('请选择一条数据!');
                    		}
                    	}).modal('show');
                	});
                }else{
                	$('#' + that.$el[0].id + column.field + rowindex).off('blur').on('blur',function(e,params){
                        var data = that.getData();
                        var index = $(this).parents('tr[data-index]').data('index');
                        var row = data[index];
                        var oldValue = row[column.field];
                        row[column.field] = $(this).val();
                        validForm(that.$el[0].id + column.field + 'form');
                    });
                	validForm(that.$el[0].id + column.field + 'form');
                }
            });
        }
        this.trigger('editable-init');
    };
}(jQuery);
