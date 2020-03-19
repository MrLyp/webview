(function() {
    if (window.WebViewJavascriptBridge) {
        return;
    }
    var receiveMessageQueue = [];
    var messageHandlers = {};
    var responseCallbacks = {};
    var uniqueId = 1;

  function _doSendMessage(method, args, callback) {
      var hasCallback = typeof callback == 'function';
      var callbackId = hasCallback?'cb_' + (uniqueId++) + '_' + new Date().getTime():0;
      if (hasCallback) {
        responseCallbacks[callbackId] = callback;
      }
      if(!args || typeof args !== 'object'){
        args = {};
      }
      args = JSON.stringify(args);
      var bridgeUrl = 'nb://nativebridge?method=' + method + '&args=' + encodeURIComponent(args) + '&callbackId=' + callbackId;
      window.prompt(bridgeUrl, '');
  }

  function _send(method, args){
    args = args || {};
    var self = this;
    var _success = args.success;
    var _fail = args.fail;
    var _handle = args.handle;

    var fail = function(result){
      console.log('调用失败 ' + method + result.errMsg);
      _fail && _fail.call(self, result);
    }

    var success = function(result){
      console.log('调用成功 ' + method + result);
      _success && _success.call(self, result);
    }

    var handle = function(result){
      console.log('回调 ' + method, result);
      _handle && _handle.call(self, result);
    }

    var callback = (_success || _fail || _handle) ? function(result){
      var status = result.status;
      if(result.result != "next"){
        delete result.result;
      }
      if(status == "success"){
        success && success(result);
      }else if(status == "action"){
        handle && handle(result);
      }else{
        fail && fail(result);
      }
    } : null;
    _doSendMessage(method, args, callback);
  }

    function registerHandler(handlerName, handler) {
        messageHandlers[handlerName] = handler;
    }
    function callHandler(handlerName, data, responseCallback) {
    }

    function callback(callbackId, originValue) {
        setTimeout(function() {
            if (callbackId && responseCallbacks[callbackId]) {
                var retValue = JSON.parse(originValue);
                var callback = responseCallbacks[callbackId];
                callback && callback.call(this,retValue);
                if(retValue.result == "complete" || retValue.result == "error"){
                  responseCallbacks[callbackId] = null;
                  delete responseCallbacks[callbackId];
                }
            }
        });
    }

    function ready() {
        var doc = document;
        var readyEvent = doc.createEvent('Events');
        readyEvent.initEvent('WebViewJavascriptBridgeReady');
        readyEvent.bridge = WebViewJavascriptBridge;
        doc.dispatchEvent(readyEvent);
    }

    var WebViewJavascriptBridge = window.WebViewJavascriptBridge = {
        callMethod: _send,
        registerHandler: registerHandler,
        callHandler: callHandler,
        callback: callback,
        ready: ready
    };
})();