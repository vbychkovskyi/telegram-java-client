package com.github.vbychkovskyi.tg.client.handle;

import org.drinkless.tdlib.TdApi;

public class DefaultNoOperationHandler implements Handler<TdApi.Object> {

  @Override
  public void onResult(final TdApi.Object object) {
    onTypedResult(object);
  }

  @Override
  public void onTypedResult(final TdApi.Object object) {
    System.out.println("Received update :" + object.getConstructor());
  }

  @Override
  public int getConstructor() {
    throw new UnsupportedOperationException();
  }
}
