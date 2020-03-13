<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function query(){
	var url="/brand/list?name="+$("#queryName").val();
	$("#main").load(url);
}

function selectedAll(){
	var checked = $("#allSel").prop("checked")
	$("[name=ids]").each(function(){
			$(this).prop("checked",checked)
		}
	)
}

function selectedOne(){
	var allSelected = $("[name=ids]").length==$("[name=ids]:checked").length;
	$("#allSel").prop("checked",allSelected)
}

function selAll(flag){
	
 	if(flag==1){
		//全选
		$("[name=ids]").each(function(){
			$(this).prop("checked",true)
		}
		)
	}
	
	if(flag==2){
		//全不选
		$("[name=ids]").each(function(){
			$(this).prop("checked",false)
		})
	}
	if(flag==3){
		//反选
		$("[name=ids]").each(function(){
			var ch = !$(this).prop("checked")
			$(this).prop("checked",ch)
		}
		)
	} 
	// 判断是否所有的都被选中了
	var allSelected = $("[name=ids]").length==$("[name=ids]:checked").length;
	//设置全选的框
	$("#allSel").prop("checked",allSelected)
}

function goPage(pagenum){
	var url="/brand/list?name="+$("#queryName").val()+'&page='+pagenum;
	$("#main").load(url);
}

function delBrand(id){
	if(confirm("您确认删除该条数据么？")){
		$.post("/brand/delBrand",{id:id},function(data){
			if(data="true"){
				alert("删除成功")
				refresh();
			}else{
				alert("删除失败")
			}
			
		});
	}else{
		return;
	}
}

function delBatch(){
	
	if($("[name=ids]:checked").length<=0){
		alert("没有数据被选中，不能删除")
		return;
	}
	
	
	
	// 组织删除的数据
	var delIds  = new Array();
	$("[name=ids]:checked").each(function(){
		delIds.push($(this).val());
	})
	
	
	if(confirm("您确认删除这些数据么？")){
		$.post("/brand/delBatchs",{ids:delIds},function(data){
			if(data="true"){
				alert("删除成功")
				refresh();
			}else{
				alert("删除失败")
			}
			
		});
	}else{
		return;
	}
}

function commitBrand(){
	var formData = new FormData($("#addBrand")[0]);
	$.ajax({
		  url:"/brand/add",
		  data:formData,
		  processData : false,
		  contentType : false,
		  type:"post",
		  success:function(data){
			 if(data=="true"){
				 alert("数据提交成功");
				 $('#staticBackdrop').modal('hide')
			 }else{
				 alert("数据保存失败")
			 }
			 
		  }
	})
}

/* function resetAddForm(){
	$(".form-group-proper").each(function(){
		$(this).remove();
	})
	addindex=1;
	$("#brandName").val("")
	$("#firstChar").val("")
	
} */

function refresh(){
	var url="/brand/list?name=${queryName}"+'&page=${pageInfo.pageNum}';
	$("#main").load(url);
}

$('#staticBackdrop').on('shown.bs.modal', function (e) {
	resetAddForm();
})

$('#staticBackdrop').on('hidden.bs.modal', function (e) {
	refresh();
})

$('#staticBackdropUpdate').on('hidden.bs.modal', function (e) {
		  // do something...
		refresh();
})
function openUpdateBrand(id){
		
		//清空数据
		$(".form-group-proper").each(function(){
			$(this).remove();
		})
		addindex=0;
		$("#upspecName").val("")
		
		
		$.post("/brand/getBrand",{id:id},function(data){
			//规格的id
			$("#id").val(data.id)
			$("#name").val(data.name)
			$("#first").val(data.firstChar)
			// 添加规格的选项
			addindex=0;		
		});
		
		$("#staticBackdropUpdate").modal('show')
	}
	
function commitUpdateSpec(){
	
	//addspec
	var formData = new FormData($("#updateBrand")[0]);
	$.ajax({url:"/brand/update",
		  data:formData,
		  processData : false,
		  contentType : false,
		  type:"post",
		  success:function(data){
			 if(data=="success"){
				 alert("数据提交成功");
				 $('#staticBackdropUpdate').modal('hide')
			 }else{
				 alert("数据保存失败")
			 }
		  }
	})
}
</script>

<div>
	<input id="queryName" value="${queryName}" />
	<button type="button" class="btn btn-primary" onclick="query()">
		查询</button>

	<button type="button" class="btn btn-primary" onclick="delBatch()">
		批量删除</button>

	<button type="button" class="btn btn-primary" data-toggle="modal"
		data-target="#staticBackdrop">添加</button>
</div>

<!-- <div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
      </div>
      <div class="modal-body">
        	<form id="addBrand">
        		<div class="form-group">
    				<label for="specName">规格名称</label>
    				<input type="text" class="form-control" name="name" id="brandName" aria-describedby="specNamelHelp">
    				<small id="specNamelHelp" class="form-text text-muted">请输入品牌名称</small>
  				</div>
  				<div class="form-group">
    				<label for="name">品牌首字母</label>
    				<input type="text" class="form-control" name="firstChar" id="firstChar" aria-describedby="specNamelHelp">
    				<small id="specNamelHelp" class="form-text text-muted">请输入品牌首字母</small>
  				</div>
        	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="commitBrand()">提交</button>
      </div>
    </div>
  </div>
</div> -->

<!-- 添加 -->
<div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">添加品牌</h5>
         
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        	
      </div>
      <div class="modal-body">
        	<form id="addBrand">
        		 <div class="form-group">
    				<label for="specName">规格名称</label>
    				<input type="text" class="form-control" name="name" id="brandName" aria-describedby="specNamelHelp">
    				<small id="specNamelHelp" class="form-text text-muted">请输入品牌名称</small>
  				</div>
  				<div class="form-group">
    				<label for="name">品牌首字母</label>
    				<input type="text" class="form-control" name="firstChar" id="firstChar" aria-describedby="specNamelHelp">
    				<small id="specNamelHelp" class="form-text text-muted">请输入品牌首字母</small>
  				</div>  				
        	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="commitBrand()">提交</button>
      </div>
    </div>
  </div>
</div>

<!-- 修改 -->
<div class="modal fade" id="staticBackdropUpdate" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">修改品牌</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        	
      </div>
      <div class="modal-body">
        	<form id="updateBrand">
        		 <input type="hidden" name="id" id="id">
        		 <div class="form-group">
    				<label for="specName">品牌名称</label>
    				<input type="text" class="form-control" name="name" id="name" aria-describedby="specNamelHelp">
    				<small id="specNamelHelp" class="form-text text-muted">请输入品牌名称</small>
  				</div>
  				<div class="form-group">
    				<label for="specName">品牌名称</label>
    				<input type="text" class="form-control" name="firstChar" id="first" aria-describedby="specNamelHelp">
    				<small id="specNamelHelp" class="form-text text-muted">请输入品牌名称</small>
  				</div>
        	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="commitUpdateSpec()">提交</button>
      </div>
    </div>
  </div>
</div>


<table class="table">

	<tr>
		<th><input type="checkbox" id="allSel" onchange="selectedAll()">
			<button type="button" class="btn btn-info btn-sm" onclick="selAll(1)">全选</button>
			<button type="button" class="btn btn-info btn-sm" onclick="selAll(2)">全不选</button>
			<button type="button" class="btn btn-info btn-sm" onclick="selAll(3)">反选</button>
		</th>
		<th>ID</th>
		<th>品牌名称</th>
		<th>首字母</th>
	</tr>
	<c:forEach items="${pageInfo.list}" var="brand">
		<tr>
			<td><input type="checkbox" name="ids" value="${brand.id}"
				onchange="selectedOne()"> ${brand.id}</td>
			<td>${brand.id}</td>
			<td>${brand.name}</td>
			<td>${brand.firstChar}</td>
			<td>
				<button type="button" class="btn btn-danger" onclick="delBrand(${brand.id})">删除</button>
				<button type="button" class="btn btn-warning" onclick="openUpdateBrand(${brand.id})">修改</button>
			</td>
		</tr>
	</c:forEach>

	<!-- pageInfo -->
	<tr>
		<td colspan="3"><jsp:include page="../common/page.jsp"></jsp:include>
		</td>
	</tr>
</table>