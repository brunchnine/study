RequestParameter requestParam =
        new RequestParameter.Builder().setUrl(new URI.Builder().setPath(URI.CARD_DETAIL).build())
            .setPostParams(cardIdxReq)
            .setTransactionType(transactionType)
            .setErrorListener(this)
            .setSuccessListener(this)
            .build();
    // ApiManager 를 이용하여 RequestParameter 를 전송한다.
    mApiManager.request(requestParam


    	RequestParameter request = new RequestParameter(url, cardIdxReq, transactionType, this, this);