package com.starbucks.co.constants;

import com.starbucks.co.application.StarbucksApplication;
import com.starbucks.co.config.Config;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.protocol.HTTP;

import java.util.List;

public class URI {

  /**
   * MSR Server <p/> 본섭 scheme, authority, domain
   */
  public static String scheme = "http";
  public static String securityScheme = "https";

  public static String securityAuthority;
  public static String authority;

  public static String domain = "msr.istarbucks.co.kr";

  /**
   * XOP Server
   */
  public static String sXopSecurityAuthority;

  public static String sXopAuthority;
  /* real server */
  public static String realSecurityAuthority = "msr.istarbucks.co.kr:6443/appif";
  public static String realSXopSecurityAuthority = "msr.istarbucks.co.kr:6443/appif";
  public static String sWebAuthority = "https://www.istarbucks.co.kr:7443";
  /* pilot server */
//  public static String realSecurityAuthority = "msr.istarbucks.co.kr:6443/appifp";
//  public static String realSXopSecurityAuthority = "msr.istarbucks.co.kr:6443/appifp";
//  public static String sWebAuthority = "https://www.istarbucks.co.kr:8443";

  static {
    //테스트 모드일때는 테섭으로..
    if (Config.DEV == StarbucksApplication.sConfig.getServer()) {

      securityScheme = "http";
      securityAuthority = "60.196.0.86:8088/appif";

      authority = "60.196.0.86:8088/appif";
      domain = "60.196.0.86:8088/appif";

      sXopSecurityAuthority = "60.196.0.86:8088/appif";
      sXopAuthority = "60.196.0.86:8088/appif";
    }

    //모든 http를 https로 변환한다.
    if (Config.REAL == StarbucksApplication.sConfig.getServer()) {
      scheme = securityScheme;

      /* real app store 전용 */
      authority = realSecurityAuthority;
      sXopAuthority = realSXopSecurityAuthority;
    }
  }

  /**
   * @category Member
   * @serialField Login, SignUp
   */

  /**
   * Account 관련 전문
   */
  /* 회원 로그인 전문 */
  public final static String MEMBER_LOGIN = "/auth/login.do";

  /* 회원 로그아웃 전문 */
  public final static String MEMBER_LOGOUT = "/auth/logout.do";

  /* 닉네임 수정 전문 */
  public final static String NICK_NAME_MODIFY = "/common/nicknameModify.do";

  /* 임직원 체크 */
  public final static String EMPLOYEE_NUMBER_CHECK = "/xo/empCheck.do";

  /* 임직원 등록 */
  public final static String EMPLOYEE_NUMBER_REGISTER = "/xo/empRegister.do";
  /* 임직원 번호 조회 */
  public final static String EMPLOYEE_NUMBER_LOAD = "/common/empNo.do";

  public final static String NON_MEMBER_LOGIN = "/nonmember/auth/startup.do";
  public final static String NON_MEMBER_LOGOUT = "/auth/nonmember/logout.do";
  public final static String NON_MEMBER_LOGOUT_TEST = "/nonmember/auth/logout.do";

  /**
   * 홈 전문
   */
  /* 버전 체크 전문 */
  public final static String APP_VERSION = "/common/version.do";

  /* 대쉬 보드 전문 */
  public final static String DASH_BOARD = "/common/dashboard.do";

  /* 퀵오더 전문 */
  public final static String QUICK_ORDER_LIST = "/xo/myFavoriteOrderList.do";

  /* 퀵오더 결제 */
  public final static String QUICK_ORDER_PAYMENT = "/xo/myFavoriteOrderPayment.do";

  /* 카트 메뉴 카운트 */
  public final static String CART_MENU_COUNT = "/xo/cartMenuCount.do";

  /**
   * 카드 전문
   */
  /* 카드 리스트 전문 */
  public final static String CARD_LIST = "/card/list.do";

  /* 카드 상세 전문 */
  public final static String CARD_DETAIL = "/card/detail.do";

  /* 카드 등록 해지 전문 */
  public final static String RENOVATION = "/card/renovation.do";

  /* 카드 삭제 전문 */
  public final static String DELETION = "/card/deletion.do";

  /* 대표 카드 전문 */
  public final static String DELEGATE_CARD = "/card/delegateCard.do";

  /* 중지 / 환불 가능 여부 조회 */
  public final static String REFUNDABLE = "/card/refund/refundable.do";

  /* 카드 중지 (분실신고) */
  public final static String REPORT_LOSS = "/card/reportLoss.do";

  /* 4.6.9. 골드카드 신청내역 조회 */
  public final static String GOLD_CARD_REGIST_INFO = "/goldCard/applicationDetail.do";

  /**
   * 비회원 저문
   */
  /* 비 회원 카드 목록 전문 */
  public final static String NONMEMBER_CARDLIST = "/nonmember/card/list.do";

  /* 비 회원 카드 상세 전문 */
  public final static String NONMEMBER_CARD_DETAIL = "/nonmember/card/detail.do";

  /* 비 회원 카드 카드등록 해지 전문 */
  public final static String NONMEMBER_RENOVATION = "/nonmember/card/renovation.do";
  /**
   * 사이렌오더 전문
   */
  /* 사이렌오더 인트로 (사이렌 세션갱신) 전문 */
  public final static String SIREN_ORDER_INTRO = "/xo/intro.do";

  /* 사이렌오더 메뉴 카테고리 목록 조회 */
  public final static String SIREN_CATEGORY_LIST = "/xo/categoryList.do";

  /* 사이렌오더 카테고리별 SKU 목록 조회 */
  public final static String SKU_LIST = "/xo/skuList.do";

  /* 사이렌오더  SKU 상세 정보 조회 */
  public final static String SKU_VIEW = "/xo/skuView.do";

  /* 사이렌오더  SKU Cart 메뉴에 추가 */
  public final static String CART_MENU_ADD = "/xo/cartMenuAdd.do";

  /* 5.2.4. 커스텀 정보 카테고리 조회 */
  public final static String CUSTOM_CATEGORY_LIST = "/xo/customCategoryList.do";

  /* 5.2.4. 커스텀 정보 카테고리 조회 */
  public final static String CUSTOM_SKU_LIST = "/xo/customSkuList.do";

  /* 5.5.2. MSR 쿠폰 목록 조회 */
  public final static String XO_COUPON_LIST = "/xo/msrCouponList.do";

  /* 5.7.3. 바로 주문하기 */
  public final static String ORDER_FAST = "/xo/orderFast.do";

  /* 5.4.2. 카트 상세 조회 */
  public final static String XO_CART_VIEW = "/xo/cartView.do";

  /* 5.5.3. 제휴카드 체크 */
  public final static String PARTNER_MEMBER_CHECK = "/xo/partnerMemberCheck.do";

  /* 5.5.1. 결제 수단 조회 */
  public final static String SIREN_ORDER_PAYMENT_METHOD = "/xo/payMethod.do";

  /* 5.5.5. 결제 */
  public final static String SIREN_ORDER_PAYMENT = "/xo/payment.do";

  /* 리워드 */
  public final static String STARBUCKS_REWARD_PAGE = sWebAuthority + "/app/msr/gate.do";

  /* 공지사항 */
  public final static String WEB_NOTICE_PAGE = sWebAuthority + "/app/whats_new/notice_list.do";

  /* 이용안내 */
  public final static String WEB_USE_GUIDE_PAGE = sWebAuthority + "/app/footer/tip.do";

  /* FAQ */
  public final static String WEB_FAQ_PAGE = sWebAuthority + "/app/util/faq.do";

  /* 이용약관 */
  public final static String WEB_TERMS_OF_USE_PAGE = sWebAuthority + "/app/information.do";

  /* 개인정보 취급방침 */
  public final static String WEB_POLICIES_PAGE = sWebAuthority + "/app/footer/etc/privacy.do";

  /* 5.5.5. 결제 */
  public final static String SIREN_PAYMENT_PG = "/xo/paymentPg.do";

  /* 발신번호 사전등록 체크 */
  public final static String PRE_REGIST_CHECK = sWebAuthority + "/interface/getPreRegistFlag.do";

  /* 발신번호 사전등록 */
  public final static String PRE_REGIST_NUMBER = sWebAuthority + "/app/phoneCert/call.do";

  public static class Builder {
    private String scheme = URI.scheme;
    private String authority = URI.authority;
    private String path = null;
    private String query = null;

    /**
     * 스키마를 지정한다(http,https)
     *
     * @see URI#securityScheme
     * @see URI#scheme
     */
    public Builder setScheme(String scheme) {
      this.scheme = scheme;
      return this;
    }

    /**
     * 인증방식을 지정한다(host+port)
     *
     * @see URI#securityAuthority
     * @see URI#authority
     */
    public Builder setAuthority(String authority) {
      this.authority = authority;
      return this;
    }

    /**
     * 경로를 지정한다
     */
    public Builder setPath(String path) {
      this.path = path;
      return this;
    }

    /**
     * 쿼리를 지정한다
     */
    public Builder setQuery(String query) {
      this.query = query;
      return this;
    }

    public Builder setQuery(List<NameValuePair> parameters) {
      this.query = URLEncodedUtils.format(parameters, HTTP.UTF_8);
      return this;
    }

    /**
     * parameters를 encoding하여 쿼리를 지정한다
     */
    public Builder setQuery(List<NameValuePair> parameters, String encoding) {
      this.query = URLEncodedUtils.format(parameters, encoding);
      return this;
    }

    /**
     * 매개 변수를 조합해 url을 생성한다
     */
    public String build() {
      StringBuilder uri = new StringBuilder();
      if (scheme != null) {
        uri.append(scheme);
        uri.append(":");
      }

      if (authority != null) {
        uri.append("//");
        uri.append(authority);
      }

      if (path != null) {
        uri.append(path);
      }

      if (query != null) {
        uri.append("?");
        uri.append(query);
      }

      return uri.신toString();
    }
  }
}
