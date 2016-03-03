function enableDragg() {  
		$('.tree-node').draggable({  
			revert: true,  
			proxy: function(source){	//这里保证拖到其他layout过程中可见
				var p = $(source).clone();
				p.appendTo('body');
				return p;
			},
			cursor: 'auto',
			disabled: false,
			onStartDrag:function(){
                    $(this).draggable('proxy').addClass('dp');	//dp中的z-index: 999;保证在本layout也可见
			}
		});  
};