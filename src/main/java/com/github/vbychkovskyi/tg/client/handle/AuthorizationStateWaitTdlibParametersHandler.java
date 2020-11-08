package com.github.vbychkovskyi.tg.client.handle;

import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationStateWaitTdlibParametersHandler
  implements Handler<TdApi.AuthorizationStateWaitTdlibParameters> {

  private final Client client;
  private final Client.ResultHandler resultHandler;

  @Override
  public void onResult(final TdApi.Object object) {
    onTypedResult((TdApi.AuthorizationStateWaitTdlibParameters) object);
  }

  @Override
  public void onTypedResult(final TdApi.AuthorizationStateWaitTdlibParameters object) {
    final TdApi.TdlibParameters tdlibParameters = new TdApi.TdlibParameters();
    final TdApi.SetTdlibParameters setTdlibParameters = new TdApi.SetTdlibParameters(tdlibParameters);

    client.send(setTdlibParameters, resultHandler);
  }

  @Override
  public int getConstructor() {
    return TdApi.AuthorizationStateWaitTdlibParameters.CONSTRUCTOR;
  }
}
