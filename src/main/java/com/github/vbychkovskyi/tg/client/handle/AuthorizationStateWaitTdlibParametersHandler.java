package com.github.vbychkovskyi.tg.client.handle;

import java.util.Scanner;

import org.drinkless.tdlib.Client;
import org.drinkless.tdlib.TdApi;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationStateWaitTdlibParametersHandler
  implements Handler<TdApi.UpdateAuthorizationState> {

  private final Client client;
  private final Client.ResultHandler resultHandler;

  @Override
  public void onResult(final TdApi.Object object) {
    onTypedResult((TdApi.UpdateAuthorizationState) object);
  }

  @Override
  public void onTypedResult(final TdApi.UpdateAuthorizationState object) {
    if (object.authorizationState.getConstructor() == TdApi.AuthorizationStateWaitTdlibParameters.CONSTRUCTOR) {
      setTdlibParameters();
    }
    if (object.authorizationState.getConstructor() == TdApi.AuthorizationStateWaitEncryptionKey.CONSTRUCTOR) {
      checkDatabaseEncryptionKey();
    }
    if (object.authorizationState.getConstructor() == TdApi.AuthorizationStateWaitPhoneNumber.CONSTRUCTOR) {
      final TdApi.SetAuthenticationPhoneNumber params = new TdApi.SetAuthenticationPhoneNumber();
      params.phoneNumber = System.getProperty("phone_number");
      client.send(params, resultHandler);
    }

    if (object.authorizationState.getConstructor() == TdApi.AuthorizationStateWaitCode.CONSTRUCTOR) {
      final TdApi.CheckAuthenticationCode params = new TdApi.CheckAuthenticationCode();
      params.code = readCode();
      client.send(params, resultHandler);
    }

    if (object.authorizationState.getConstructor() == TdApi.AuthorizationStateReady.CONSTRUCTOR) {

      final TdApi.GetChats getChats = new TdApi.GetChats();
      getChats.limit = 30;

      client.send(getChats, resultHandler);

      final TdApi.SendMessage sendMessage = new TdApi.SendMessage();
      sendMessage.chatId = -1001211410985L;
      sendMessage.inputMessageContent = new TdApi.InputMessageText(new TdApi.FormattedText(
        "/game",
        null
      ), false, true);

      client.send(sendMessage, resultHandler);
    }
  }

  private String readCode() {
    final Scanner scanner = new Scanner(System.in);

    return scanner.next();
  }

  private void checkDatabaseEncryptionKey() {
    final TdApi.CheckDatabaseEncryptionKey key = new TdApi.CheckDatabaseEncryptionKey();
    client.send(key, resultHandler);
  }

  private void setTdlibParameters() {
    final TdApi.TdlibParameters tdlibParameters = new TdApi.TdlibParameters();
    tdlibParameters.apiId = Integer.parseInt(System.getProperty("API_ID"));
    tdlibParameters.apiHash = System.getProperty("API_HASH");
    tdlibParameters.databaseDirectory = "~/tmp";
    tdlibParameters.useMessageDatabase = false;
    tdlibParameters.useSecretChats = false;
    tdlibParameters.systemLanguageCode = "en-GB";
    tdlibParameters.deviceModel = "AMD x64 Desktop";
    tdlibParameters.systemVersion = "Ubuntu 20.04.1 LTS";
    tdlibParameters.applicationVersion = "0.0.1";

    final TdApi.SetTdlibParameters setTdlibParameters = new TdApi.SetTdlibParameters(tdlibParameters);

    client.send(setTdlibParameters, resultHandler);
  }

  @Override
  public int getConstructor() {
    return TdApi.UpdateAuthorizationState.CONSTRUCTOR;
  }
}
