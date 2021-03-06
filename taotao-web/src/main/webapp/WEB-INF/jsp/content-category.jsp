<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <ul id="contentCategory" class="easyui-tree">  </ul>
</div>
<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<script type="text/javascript">
$(function(){
	$("#contentCategory").tree({
		url : '/content/category/list',
		animate: true,
		method : "GET",
		//右击鼠标触发
		onContextMenu: function(e,node){
			//关闭原来的鼠标右击事件
            e.preventDefault();
			//右击鼠标选中的节点
            $(this).tree('select',node.target);
			//展示菜单
            $('#contentCategoryMenu').menu('show',{
                left: e.pageX,//在鼠标位置显示
                top: e.pageY
            });
        },
        onAfterEdit : function(node){
        	var _tree = $(this);
        	if(node.id == 0){
        		// 新增节点
        		$.post("/content/category/create",{parentId:node.parentId,name:node.text},function(data){
        			if(data.status == 200){
        				_tree.tree("update",{
            				target : node.target,
            				id : data.data.id
            			});
        			}else{
        				$.messager.alert('提示','创建'+node.text+' 分类失败!');
        			}
        		});
        	}else{
        		$.post("/content/category/update",{id:node.id,name:node.text});
        	}
        }
	});
});
function menuHandler(item){
	//获取树
	var tree = $("#contentCategory");
	//获取被选中的节点
	var node = tree.tree("getSelected");
	if(item.name === "add"){
		tree.tree('append', {
            parent: (node?node.target:null),//被添加的子节点的父节点
            data: [{
                text: '新建分类',
                id : 0,//默认id为0
                parentId : node.id
            }]
        }); 
		var _node = tree.tree('find',0);//根节点
		tree.tree("select",_node.target).tree('beginEdit',_node.target);
	}else if(item.name === "rename"){
		tree.tree('beginEdit',node.target);
	}else if(item.name === "delete"){
		$.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？',function(r){
			if(r){
				$.post("/content/category/delete",{id:node.id},function(data){
					if(data.status == 200){
						tree.tree("remove",node.target);	
					}if(data.status == 201){
						$.messager.confirm('提示','父菜单不能删除','info');
					}
					
				});	
			}
		});
	}
}
</script>