# CabbageMarket

## What to Make

### 1. 유저의 지역에 해당하는 중고 물품 목록 전달
    request
    GET getUsed(userId: String)

    response
    [
        {
            usedId: "1",
            title: "ps4pro 팝니다.",
            imageUrl: "http://gdimg.gmarket.co.kr/2360305459/still/280?ver=1651648538",
            price: "300000",
            sellerId: "sasimi"
        },
        {
            usedId: "2",
            title: "ps3 팝니다.",
            imageUrl: "http://gdimg.gmarket.co.kr/2360305459/still/280?ver=1651648538",
            price: "100000",
            sellerId: "sasimi"
        },
        {
            usedId: "3",
            title: "ps2 팝니다.",
            imageUrl: "http://gdimg.gmarket.co.kr/2360305459/still/280?ver=1651648538",
            price: "20000",
            sellerId: "sasimi"
        },
    ]

### 1-1. 구매하기
    request
    GET buy(userId: String, usedId: String)

    response
    {
        status: "ok"
    }

### 2. 중고 올리기 (글쓰기)
    request
    POST makeUsed(userId: String)
    body
    {
        title: "ps-original 팝니다.",
        imageUrl: "http://gdimg.gmarket.co.kr/2360305459/still/280?ver=1651648538",
        price: "20000000",
        sellerId: "sasimi"
    }

### 2-1. 상품 상세정보
    request
    GET getUsedInfoDetail(usedId)

    response
    {
        usedId: "3",
        title: "ps2 팝니다.",
        imageUrl: "http://gdimg.gmarket.co.kr/2360305459/still/280?ver=1651648538",
        price: "20000",
        sellerId: "sasimi"
    }

 ### 3. 채팅 - 말 가져오기 (히스토리, 상대의 말)

 ### 4. 채팅 - 말 하기

 ### 5. 마이페이지 (구매목록, 지역, 이름, 전화번호)
    request
    GET getMyInfo(userid: String) 

    response
    {
        orderList: [1,2,3,4],
        geo: {
            x: 128.028737474,
            y: 38.2393462873
        },
        name: "",
        tel: ""
    }

### 6. 마이페이지 수정 (지역좌표, 이름, 전화번호)
    request
    POST modifyMyInfo(userid: String) 
    body 
    {
        geo: {
            x: 128.00001,
            y: 38.999999
        },
        name: "하민",
        tel: "0109999888"
    }

    response 
    {
        status: "ok"
    }
