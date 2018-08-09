function createXMLHTTPRequest() {
	// 1.创建XMLHttpRequest对象
	// 这是XMLHttpReuquest对象无部使用中最复杂的一步
	// 需要针对IE和其他类型的浏览器建立这个对象的不同方式写不同的代码
	var xmlHttpRequest;
	if (window.XMLHttpRequest) {
		// 针对FireFox，Mozillar，Opera，Safari，IE7，IE8
		xmlHttpRequest = new XMLHttpRequest();
		// 针对某些特定版本的mozillar浏览器的BUG进行修正
		if (xmlHttpRequest.overrideMimeType) {
			xmlHttpRequest.overrideMimeType("text/xml");
		}
	} else if (window.ActiveXObject) {
		// 针对IE6，IE5.5，IE5
		// 两个可以用于创建XMLHTTPRequest对象的控件名称，保存在一个js的数组中
		// 排在前面的版本较新
		var activexName = [ "MSXML2.XMLHTTP", "Microsoft.XMLHTTP" ];
		for (var i = 0; i < activexName.length; i++) {
			try {
				// 取出一个控件名进行创建，如果创建成功就终止循环
				// 如果创建失败，回抛出异常，然后可以继续循环，继续尝试创建
				xmlHttpRequest = new ActiveXObject(activexName[i]);
				if (xmlHttpRequest) {
					break;
				}
			} catch (e) {
			}
		}
	}
	return xmlHttpRequest;
}
(function($) {
	$.fn.mergeCell = function(options) {
		return this.each(function() {
			var cols = options.cols;
			for (var i = cols.length - 1; cols[i] != undefined; i--) {
				mergeCell($(this), cols[i]);
			}
			dispose($(this));
		});
	};
	function mergeCell($table, colIndex) {
		$table.data('col-content', ''); // 存放单元格内容
		$table.data('col-rowspan', 1); // 存放计算的rowspan值 默认为1
		$table.data('col-td', $()); // 存放发现的第一个与前一行比较结果不同td(jQuery封装过的),
									// 默认一个"空"的jquery对象
		$table.data('trNum', $('tbody tr', $table).length); // 要处理表格的总行数,
															// 用于最后一行做特殊处理时进行判断之用
		// 我们对每一行数据进行"扫面"处理 关键是定位col-td, 和其对应的rowspan
		$('tbody tr', $table).each(
			function(index){
				// td:eq中的colIndex即列索引
				var $td = $('td:eq(' + colIndex + ')', this);
				var currentContent = $td.html();
				// 取出前一个单元格的内容
				var preContent = '';
				if(0 != colIndex){
					preContent = $('td:eq(' + (colIndex - 1) + ')', this).html();
				}
				
				// 第一次时走此分支
				if ($table.data('col-content') == '') {
					$table.data('col-content', preContent + currentContent);
					$table.data('col-td', $td);
				} else {
					// 上一行与当前行内容相同
					if ($table.data('col-content') == preContent + currentContent) {
						// 上一行与当前行内容相同则col-rowspan累加, 保存新值
						var rowspan = $table.data('col-rowspan') + 1;
						$table.data('col-rowspan', rowspan);
						// 值得注意的是 如果用了$td.remove()就会对其他列的处理造成影响
						$td.hide();
						// 最后一行的情况比较特殊一点
						// 比如最后2行 td中的内容是一样的,
						// 那么到最后一行就应该把此时的col-td里保存的td设置rowspan
						if ((index + 1) == $table.data('trNum'))
							$table.data('col-td').attr('rowspan',$table.data('col-rowspan'));
					} else { // 上一行与当前行内容不同
						// col-rowspan默认为1, 如果统计出的col-rowspan没有变化, 不处理
						if ($table.data('col-rowspan') != 1) {
							$table.data('col-td').attr('rowspan',$table.data('col-rowspan'));
						}
						// 保存第一次出现不同内容的td, 和其内容, 重置col-rowspan
						$table.data('col-td', $td);
						$table.data('col-content', preContent + $td.html());
						$table.data('col-rowspan', 1);
					}
				}
			}
		);
	}
	// 同样是个private函数 清理内存之用
	function dispose($table) {
		try
		{
			$table.removeData();
		}
		catch(e)
		{
			
		}
	}
})(jQuery);