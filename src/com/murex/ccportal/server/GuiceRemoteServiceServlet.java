package com.murex.ccportal.server;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Injector;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author hsuleiman
 *         Date: 12/9/11
 *         Time: 9:25 AM
 */
@Singleton
public class GuiceRemoteServiceServlet extends RemoteServiceServlet {
  @Inject
  private Injector injector;

  @Override
  public String processCall(String payload) throws SerializationException {
    // First, check for possible XSRF situation
    checkPermutationStrongName();

    try {
      RPCRequest rpcRequest = RPC.decodeRequest(payload, null, this);
      RemoteService service = getServiceInstance(rpcRequest.getMethod().getDeclaringClass());
      onAfterRequestDeserialized(rpcRequest);
      return RPC.invokeAndEncodeResponse(service, rpcRequest.getMethod(),
          rpcRequest.getParameters(), rpcRequest.getSerializationPolicy(),
          rpcRequest.getFlags());
    } catch (IncompatibleRemoteServiceException ex) {
      log(
          "An IncompatibleRemoteServiceException was thrown while processing this call.",
          ex);
      return RPC.encodeResponseForFailure(null, ex);
    } catch (RpcTokenException tokenException) {
      log("An RpcTokenException was thrown while processing this call.",
          tokenException);
      return RPC.encodeResponseForFailure(null, tokenException);
    }
  }

  @SuppressWarnings({"unchecked"})
  private RemoteService getServiceInstance(Class serviceClass) {
    return (RemoteService) injector.getInstance(serviceClass);
  }
}
