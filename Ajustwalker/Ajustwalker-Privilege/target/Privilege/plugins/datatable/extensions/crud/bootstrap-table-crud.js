(function ($) {
    'use strict';
    var sprintf = $.fn.bootstrapTable.utils.sprintf;

    var crudFunctions = {
        addline:'<div class="crud btn-group btn-group-sm"><button class="btn btn-success type="button" onclick="%s();"><i class="glyphicon glyphicon-plus icon-share"></i></button></div>',
        removeline:'<div class="crud btn-group btn-group-sm"><button class="btn btn-danger type="button" onclick="%s();"><i class="glyphicon glyphicon-minus icon-share"></i></button></div>',
        add:'<div class="crud btn-group btn-group-sm"><button class="btn btn-success type="button" onclick="%s();"><i class="glyphicon glyphicon-file icon-share"></i></button></div>',
        remove:'<div class="crud btn-group btn-group-sm"><button class="btn btn-danger type="button" onclick="%s();"><i class="glyphicon glyphicon-trash icon-share"></i></button></div>',
        exportclient:'<div class="crud btn-group btn-group-sm"><button class="btn btn-primary type="button" onclick="%s();"><i class="glyphicon glyphicon-new-window icon-share"></i></button></div>',
        exportremote:'<div class="crud btn-group btn-group-sm"><button class="btn btn-info type="button" onclick="%s();"><i class="glyphicon glyphicon-export icon-share"></i></button></div>',
        cancel:'<div class="crud btn-group btn-group-sm"><button class="btn btn-warning type="button" onclick="%s();"><i class="glyphicon glyphicon-ban-circle icon-share"></i></button></div>',
        copy:'<div class="crud btn-group btn-group-sm"><button class="btn btn-success type="button" onclick="%s();"><i class="fa fa-files-o icon-share"></i></button></div>',
        combine: 'COMBINE',
        share:'<div class="crud btn-group btn-group-sm"><button class="btn btn-default type="button" onclick="%s();"><i class="glyphicon glyphicon-share-alt icon-share"></i></button></div>'
    };

    $.extend($.fn.bootstrapTable.defaults, {
        showCrud: false,
        btnTypes:'add,remove,exportremote',
        btnFuncs:'addFunction,removeFunction,exportremoteFunction'
    });

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
    _initToolbar = BootstrapTable.prototype.initToolbar;
    BootstrapTable.prototype.initToolbar = function () {
        this.showToolbar = this.options.showCrud;
        _initToolbar.apply(this, Array.prototype.slice.apply(arguments));
        if (this.options.showCrud) {
            var that = this,
            $btnGroup = this.$toolbar.find('>.btn-group'),
            $export = $btnGroup.find('div.crud');
            if (!$export.length){
            	var btns = '';
            	var btnsDefine = this.options.btnTypes.split(',');
            	var btnsFuncDefine = this.options.btnFuncs.split(',');
            	for(var i=0;i<btnsDefine.length;i++){
            		btns = btns + sprintf(crudFunctions[btnsDefine[i]],btnsFuncDefine[i]);
            	}
                $export = $(btns).prependTo($btnGroup);
            }
        }
    };
})(jQuery);
