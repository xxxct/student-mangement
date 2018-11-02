var Obj =(function(){

    var ObjProvite = (function(){
        return{
            _setPara : function(options){
                this.bDiv = options.bDiv;
                this.aDivList = options.aDivList;
                this.mainDiv = options.mainDiv;
                this.searchLi = this.bDiv.getElementById('search') || null;
            }
        }
    })()


    var Obj = function(options){
        if (this instanceof Obj){
            ObjProvite._setPara.call(this, options);
            // ObjProvite._setPara(options); //这一句this指向有问题，上一句更正this指向
        } else {
            return new Obj(options);
        }
    }

    Obj.prototype = {
        constructor: Obj,
        install: function(){
            
            this.bindShowClass();


        },
        bindSearch(){
            _this=this;

            this.searchLi.onclick=function () {

                this.stopPropagation();
                var s='<img class="photo fl" th:src="${student.photo}" alt="">\n' +
                    '            <div class="box">\n' +
                    '                姓名：' + student.name+
                    '                <br>\n' +
                    '                学号：' + student.studentId +
                    '                <br>\n' +
                    '                性别：' + student.sex +
                    '                <br>\n' +
                    '                学院：' + student.institute +
                    '                <br>\n' +
                    '                身份证：' + student.id +
                    '            </div>';

                _this.mainDiv.innerHTML=s
            }
        },
        cutClass: function (v){
            for(var i = 0 ;i<this.aDivList.length;i++){
                if(i!=v){
                    this.bDiv[i].style.display = 'none';
                }
                
            }
            
         
        },
        
        bindShowClass:function(){
            
            var _this = this;
            for(var i = 0; i < this.aDivList.length  ; i++){
                this.aDivList[i].index = i;
                this.aDivList[i].onclick = function(){
                    
                    if(_this.bDiv[this.index].style.display == 'block'){

                        _this.bDiv[this.index].style.display = 'none';
                    }else{

                        _this.bDiv[this.index].style.display ='block';

                    }


                    _this.cutClass(this.index);     
                }
            }

        },


    }

  



    return Obj;

})()