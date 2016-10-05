// 1412582.cpp : Defines the entry point for the application.
//

#include "stdafx.h"
#include "1412582.h"
#include <windowsX.h>
#include <winuser.h>
#include <commctrl.h>
#include<time.h>
#pragma comment(linker,"\"/manifestdependency:type='win32' name='Microsoft.Windows.Common-Controls' version='6.0.0.0' processorArchitecture='*' publicKeyToken='6595b64144ccf1df' language='*'\"")
#pragma comment(lib, "ComCtl32.lib")
#define MAX_LOADSTRING 100

// Global Variables:
HINSTANCE hInst;                                // current instance
WCHAR szTitle[MAX_LOADSTRING];                  // The title bar text
WCHAR szWindowClass[MAX_LOADSTRING];            // the main window class name

// Forward declarations of functions included in this code module:
ATOM                MyRegisterClass(HINSTANCE hInstance);
BOOL                InitInstance(HINSTANCE, int);
LRESULT CALLBACK    WndProc(HWND, UINT, WPARAM, LPARAM);
INT_PTR CALLBACK    About(HWND, UINT, WPARAM, LPARAM);
//
BOOL OnCreate(HWND hWnd, LPCREATESTRUCT lpCreateStruct);
void OnCommand(HWND hWnd, int id, HWND hwndCtl, UINT codeNotify);
void OnPaint(HWND hWnd);
void OnDestroy(HWND hwnd);
//
void QuestionOperator(int Operator, WCHAR*& buffer, int i_numberA, int i_numberB, int i_result);
int RanDomNumberAToNumberB(int NumberA, int NumberB);
void VitualNumber(int &i_vitualNumber, int &i_result);
void Question(int &i_numberA, int &i_numberB, int &i_operator, int &i_result);
void WriteHightScore();
void ReadHightScore();
int APIENTRY wWinMain(_In_ HINSTANCE hInstance,
                     _In_opt_ HINSTANCE hPrevInstance,
                     _In_ LPWSTR    lpCmdLine,
                     _In_ int       nCmdShow)
{
    UNREFERENCED_PARAMETER(hPrevInstance);
    UNREFERENCED_PARAMETER(lpCmdLine);

    // TODO: Place code here.

    // Initialize global strings
    LoadStringW(hInstance, IDS_APP_TITLE, szTitle, MAX_LOADSTRING);
    LoadStringW(hInstance, IDC_MY1412582, szWindowClass, MAX_LOADSTRING);
    MyRegisterClass(hInstance);

    // Perform application initialization:
    if (!InitInstance (hInstance, nCmdShow))
    {
        return FALSE;
    }

    HACCEL hAccelTable = LoadAccelerators(hInstance, MAKEINTRESOURCE(IDC_MY1412582));

    MSG msg;

    // Main message loop:
    while (GetMessage(&msg, nullptr, 0, 0))
    {
        if (!TranslateAccelerator(msg.hwnd, hAccelTable, &msg))
        {
            TranslateMessage(&msg);
            DispatchMessage(&msg);
        }
    }

    return (int) msg.wParam;
}



//
//  FUNCTION: MyRegisterClass()
//
//  PURPOSE: Registers the window class.
//
ATOM MyRegisterClass(HINSTANCE hInstance)
{
    WNDCLASSEXW wcex;

    wcex.cbSize = sizeof(WNDCLASSEX);

    wcex.style          = CS_HREDRAW | CS_VREDRAW;
    wcex.lpfnWndProc    = WndProc;
    wcex.cbClsExtra     = 0;
    wcex.cbWndExtra     = 0;
    wcex.hInstance      = hInstance;
    wcex.hIcon          = LoadIcon(hInstance, MAKEINTRESOURCE(IDI_MY1412582));
    wcex.hCursor        = LoadCursor(nullptr, IDC_ARROW);
    wcex.hbrBackground  = (HBRUSH)(COLOR_WINDOW + 2);
    wcex.lpszMenuName   = MAKEINTRESOURCEW(IDC_MY1412582);
    wcex.lpszClassName  = szWindowClass;
    wcex.hIconSm        = LoadIcon(wcex.hInstance, MAKEINTRESOURCE(IDI_SMALL));

    return RegisterClassExW(&wcex);
}

//
//   FUNCTION: InitInstance(HINSTANCE, int)
//
//   PURPOSE: Saves instance handle and creates main window
//
//   COMMENTS:
//
//        In this function, we save the instance handle in a global variable and
//        create and display the main program window.
//
BOOL InitInstance(HINSTANCE hInstance, int nCmdShow)
{
   hInst = hInstance; // Store instance handle in our global variable

   HWND hWnd = CreateWindowW(szWindowClass, szTitle, WS_OVERLAPPEDWINDOW | WS_SIZEBOX | WS_TABSTOP | WS_HSCROLL| WS_VSCROLL
	   ,
	   100, 100, 515, 400, nullptr, nullptr, hInstance, nullptr);

   if (!hWnd)
   {
      return FALSE;
   }

   ShowWindow(hWnd, nCmdShow);
   UpdateWindow(hWnd);

   return TRUE;
}

//
//  FUNCTION: WndProc(HWND, UINT, WPARAM, LPARAM)
//
//  PURPOSE:  Processes messages for the main window.
//
//  WM_COMMAND  - process the application menu
//  WM_PAINT    - Paint the main window
//  WM_DESTROY  - post a quit message and return
//
//
LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
	switch (message) {
		HANDLE_MSG(hWnd, WM_CREATE, OnCreate);
		HANDLE_MSG(hWnd, WM_COMMAND, OnCommand);
		HANDLE_MSG(hWnd, WM_PAINT, OnPaint);
		HANDLE_MSG(hWnd, WM_DESTROY, OnDestroy);
	default:
		return DefWindowProc(hWnd, message, wParam, lParam);
	}
	return 0;
}

// Message handler for about box.
INT_PTR CALLBACK About(HWND hDlg, UINT message, WPARAM wParam, LPARAM lParam)
{
    UNREFERENCED_PARAMETER(lParam);
    switch (message)
    {
    case WM_INITDIALOG:
        return (INT_PTR)TRUE;

    case WM_COMMAND:
        if (LOWORD(wParam) == IDOK || LOWORD(wParam) == IDCANCEL)
        {
            EndDialog(hDlg, LOWORD(wParam));
            return (INT_PTR)TRUE;
        }
        break;
    }
    return (INT_PTR)FALSE;
}
////Create
HWND txtHightScore;
HWND txtScore;
HWND txtQuestion;
int  i_hightScore, i_score ,flagtemp,i_Mark;
int i_ArrayOfTwo[2];
int i_NumberA, i_numberB, i_operator, i_result, i_vitualNumber;

BOOL OnCreate(HWND hWnd, LPCREATESTRUCT lpCreateStruct)
{
	INITCOMMONCONTROLSEX icc;
	icc.dwSize = sizeof(icc);
	icc.dwICC = ICC_WIN95_CLASSES;
	InitCommonControlsEx(&icc);

	// 
	LOGFONT lf;
	GetObject(GetStockObject(DEFAULT_GUI_FONT), sizeof(LOGFONT), &lf);
	HFONT hFont1 = CreateFont(20, 0, 0, 0, FW_DONTCARE, FALSE, FALSE, FALSE, ANSI_CHARSET,
		OUT_TT_PRECIS, CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
		DEFAULT_PITCH | FF_DONTCARE, TEXT("Segoe UI Historic"));
	HFONT hFont2 = CreateFont(30, 0, 0, 0, FW_DONTCARE, FALSE, FALSE, FALSE, ANSI_CHARSET,
		OUT_TT_PRECIS, CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY,
		DEFAULT_PITCH | FF_DONTCARE, TEXT("Snap ITC"));
	HFONT hFont = CreateFont(lf.lfHeight, lf.lfWidth,
		lf.lfEscapement, lf.lfOrientation, lf.lfWeight,
		lf.lfItalic, lf.lfUnderline, lf.lfStrikeOut, lf.lfCharSet,
		lf.lfOutPrecision, lf.lfClipPrecision, lf.lfQuality,
		lf.lfPitchAndFamily, TEXT("Segoe UI"));

	HBITMAP hbitmaptitle = LoadBitmap(hInst, MAKEINTRESOURCE(IDI_TITLE));;
	HWND hwnd = CreateWindowEx(0, L"BUTTON", L" ", WS_CHILD | WS_VISIBLE | SS_CENTERIMAGE, 
		0, 0, 495, 50, hWnd, NULL, hInst, NULL); 
	SendMessage(hwnd, BM_SETIMAGE,
			(WPARAM)IMAGE_BITMAP, (LPARAM)hbitmaptitle);

	HBITMAP hbitmaps = LoadBitmap(hInst, MAKEINTRESOURCE(IDI_STATIC));;
	hwnd = CreateWindowEx(0, L"Button", L" ", WS_CHILD | WS_VISIBLE | SS_LEFT | SS_CENTER | SS_CENTERIMAGE | BS_TEXT | BS_CENTER,
		101, 50, 3, 270, hWnd, NULL, hInst, NULL);
	SendMessage(hwnd, BM_SETIMAGE, (WPARAM)IMAGE_BITMAP, (LPARAM)hbitmaps);

	//hwnd = CreateWindowEx(0, L"BUTTON", L"True", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON, 190, 250, 100, 50, hWnd, (HMENU)IDC_BUTTONTRUE, hInst, NULL);
	//SendMessage(hwnd, WM_SETFONT, WPARAM(hFont), TRUE);
	HBITMAP hIcon = LoadBitmap(hInst, MAKEINTRESOURCE(IDI_BUTTONTRUE));
	hwnd = CreateWindowEx(0, L"Button", L" ",
		WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON | BS_TEXT | BS_CENTER,
		190, 250, 95, 50, hWnd, (HMENU)IDC_BUTTONTRUE, hInst, 0);
	SendMessage(hwnd, BM_SETIMAGE,
		(WPARAM)IMAGE_BITMAP , (LPARAM)hIcon);


	//hwnd = CreateWindowEx(0, L"BUTTON", L"False", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON, 310, 250, 100, 50, hWnd, (HMENU)IDC_BUTTONFALSE, hInst, NULL);
	//SendMessage(hwnd, WM_SETFONT, WPARAM(hFont), TRUE);

	

	HBITMAP hIcon1 = LoadBitmap(hInst, MAKEINTRESOURCE(IDI_BUTTONFALSE));
	hwnd = CreateWindowEx(0, L"Button", L" ",
		WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON | BS_TEXT | BS_CENTER,
		310, 250, 95, 50, hWnd, (HMENU)IDC_BUTTONFALSE, hInst, 0);
	SendMessage(hwnd, BM_SETIMAGE,
		(WPARAM)IMAGE_BITMAP, (LPARAM)hIcon1);

	hwnd = CreateWindowEx(0, L"BUTTON", L"RESET", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON, 1, 250, 100, 50, hWnd, (HMENU)ID_RESET, hInst, NULL);
	SendMessage(hwnd, WM_SETFONT, WPARAM(hFont), TRUE);

	hwnd = CreateWindowEx(0, L"BUTTON", L"START", WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON, 1, 200, 100, 50, hWnd, (HMENU)ID_STAR, hInst, NULL);
	SendMessage(hwnd, WM_SETFONT, WPARAM(hFont), TRUE);

	hwnd = CreateWindowEx(0, L"STATIC", L"HIGHTSCORE", WS_CHILD | WS_VISIBLE | SS_LEFT | SS_CENTER | SS_CENTERIMAGE, 1, 100, 100, 20, hWnd, NULL, hInst, NULL);
	SendMessage(hwnd, WM_SETFONT, WPARAM(hFont), TRUE);

	hwnd = CreateWindowEx(0, L"STATIC", L"SCORE", WS_CHILD | WS_VISIBLE | SS_LEFT | SS_CENTER | SS_CENTERIMAGE, 1, 150, 100, 20, hWnd, NULL, hInst, NULL);
	SendMessage(hwnd, WM_SETFONT, WPARAM(hFont), TRUE);

	txtHightScore = CreateWindowEx(0, L"EDIT", L"", WS_CHILD | WS_VISIBLE | SS_LEFT | SS_CENTER | SS_CENTERIMAGE, 1, 120, 100, 20, hWnd, NULL, hInst, NULL);
	SendMessage(txtHightScore, WM_SETFONT, WPARAM(hFont), TRUE);

	
	HBITMAP hIconB = LoadBitmap(hInst, MAKEINTRESOURCE(IDI_BRAIN));
	hwnd = CreateWindowEx(0, L"BUTTON", L" ",
		WS_CHILD | WS_VISIBLE | BS_PUSHBUTTON | BS_TEXT | BS_CENTER,
		1, 51, 99, 46, hWnd, NULL, hInst, 0);
	SendMessage(hwnd, BM_SETIMAGE,
		(WPARAM)IMAGE_BITMAP, (LPARAM)hIconB);
	
	
	txtScore = CreateWindowEx(0, L"EDIT", L"", WS_CHILD | WS_VISIBLE | SS_LEFT | SS_CENTER | SS_CENTERIMAGE , 1, 170, 100, 20, hWnd, NULL, hInst, NULL);
	SendMessage(txtScore, WM_SETFONT, WPARAM(hFont), TRUE);

	txtQuestion = CreateWindowEx(0, L"EDIT", L"BRAIN WARS", WS_CHILD | WS_VISIBLE | SS_LEFT | SS_CENTER | BS_BOTTOM | SS_CENTERIMAGE, 150, 100, 300, 100, hWnd, NULL, hInst, NULL);
	SendMessage(txtQuestion, WM_SETFONT, WPARAM(hFont2), TRUE);
	return true;
}
void OnCommand(HWND hWnd, int ID, HWND hwndCtl, UINT codeNotify)
{
	WCHAR* bufferHightScore = NULL;
	WCHAR* bufferScore = NULL;
	WCHAR* bufferQuestion = NULL;

	int Array[10] = { 1,0,1,0,1,0,1,0,1,0 };
	int i_flag;
	bufferHightScore = new WCHAR[255];
	bufferScore = new WCHAR[255];
	bufferQuestion = new WCHAR[255];
	switch (ID)
	{
	case IDM_ABOUT:
		DialogBox(hInst, MAKEINTRESOURCE(IDD_ABOUTBOX), hWnd, About);
		break;
	case IDM_EXIT:
		MessageBox(0, L"Bạn Có Chắc Chắn Muốn Thoát?", L"Thông Báo!", WM_MOVE);
		WriteHightScore();
		DestroyWindow(hWnd);
		break;
	case ID_HELP_INTRODUCE:
		MessageBox(0, L"", L"", WM_MOVE);
		break;
	case ID_STAR:
		Question(i_NumberA, i_numberB, i_operator, i_result);
		VitualNumber(i_vitualNumber, i_result);
		i_flag = RanDomNumberAToNumberB(0, 9);
		flagtemp = i_flag;
		i_ArrayOfTwo[0] = i_vitualNumber;
		i_ArrayOfTwo[1] = i_result;
		QuestionOperator(i_operator, bufferQuestion, i_NumberA, i_numberB, i_ArrayOfTwo[Array[i_flag]]);
		SetWindowText(txtQuestion, bufferQuestion);
		ReadHightScore();
		SetWindowText(txtScore, L"0"); 
		wsprintf(bufferHightScore, L" %d", i_hightScore);
		SetWindowText(txtHightScore, bufferHightScore);
		i_score = 0;
		i_Mark++;
		break;
	case ID_RESET:
		Question(i_NumberA, i_numberB, i_operator, i_result);
		VitualNumber(i_vitualNumber, i_result);
		i_flag = RanDomNumberAToNumberB(0, 9);
		flagtemp = i_flag;
		i_ArrayOfTwo[0] = i_vitualNumber;
		i_ArrayOfTwo[1] = i_result;
		QuestionOperator(i_operator, bufferQuestion, i_NumberA, i_numberB, i_ArrayOfTwo[Array[i_flag]]);
		SetWindowText(txtQuestion, bufferQuestion);
		i_score = 0;
		SetWindowText(txtScore, L"0");
		wsprintf(bufferHightScore, L" %d", i_hightScore);
		SetWindowText(txtHightScore, bufferHightScore);
		WriteHightScore();
		i_Mark++;
		break;
	case IDC_BUTTONTRUE: {
		if (i_Mark == 0) {
			break;
		}
		if (i_result == i_ArrayOfTwo[Array[flagtemp]]) {
			i_score++;
			if (i_hightScore < i_score)
				i_hightScore++;
			wsprintf(bufferScore, L" %d", i_score);
			wsprintf(bufferHightScore, L" %d", i_hightScore);
			SetWindowText(txtScore, bufferScore);
			SetWindowText(txtHightScore, bufferHightScore);
			//Câu hỏi kết tiếp
			Question(i_NumberA, i_numberB, i_operator, i_result);
			VitualNumber(i_vitualNumber, i_result);
			i_flag = RanDomNumberAToNumberB(0, 9);
			flagtemp = i_flag;
			i_ArrayOfTwo[0] = i_vitualNumber;
			i_ArrayOfTwo[1] = i_result;
			QuestionOperator(i_operator, bufferQuestion, i_NumberA, i_numberB, i_ArrayOfTwo[Array[i_flag]]);
			SetWindowText(txtQuestion, bufferQuestion);
		}
		else {
			if (i_score > i_hightScore)
				WriteHightScore();
			SetWindowText(txtScore, L"0");
			i_score = 0;
			MessageBox(0, L"Bạn đã sai!Nhấn Yes và Reset để tiếp tục!", L"Thông báo", WM_MOVE);
		}
			
		break;
	}
	case IDC_BUTTONFALSE: {
		if (i_Mark == 0) {
			break;
		}
		if (i_result != i_ArrayOfTwo[Array[flagtemp]]) {
			i_score++;
			if (i_hightScore < i_score)
				i_hightScore++;
			wsprintf(bufferScore, L" %d", i_score);
			wsprintf(bufferHightScore, L" %d", i_hightScore);
			SetWindowText(txtScore, bufferScore);
			SetWindowText(txtHightScore, bufferHightScore);
			//Câu hỏi kế tiếp
			Question(i_NumberA, i_numberB, i_operator, i_result);
			VitualNumber(i_vitualNumber, i_result);
			i_flag = RanDomNumberAToNumberB(0, 9);
			flagtemp = i_flag;
			i_ArrayOfTwo[0] = i_vitualNumber;
			i_ArrayOfTwo[1] = i_result;
			QuestionOperator(i_operator, bufferQuestion, i_NumberA, i_numberB, i_ArrayOfTwo[Array[i_flag]]);
			SetWindowText(txtQuestion, bufferQuestion);
		}
		else {
			if(i_score > i_hightScore)
				WriteHightScore();
			SetWindowText(txtScore, L"0");
			i_score = 0;
			MessageBox(0, L"Bạn đã sai!Nhấn Yes và reset để tiếp tục", L"Thông báo", WM_MOVE);
		}
		break;
	}

	}
	

	if (!bufferHightScore)
		delete[] bufferHightScore;
	if (!bufferScore)
		delete[] bufferScore;
	if (!bufferQuestion)
		delete[] bufferQuestion;
}
void OnPaint(HWND hWnd)
{
	PAINTSTRUCT ps;
	HDC hdc = BeginPaint(hWnd, &ps);
	// TODO: Add any drawing code that uses hdc here...
	EndPaint(hWnd, &ps);
}
void OnDestroy(HWND hWnd) {
	PostQuitMessage(0);
}
void QuestionOperator(int Operator, WCHAR*& buffer, int i_numberA, int i_numberB, int i_result){
	if (Operator == 1)
		wsprintf(buffer, L" %d + %d = %d", i_numberA, i_numberB, i_result);
	else if(Operator == 2)
		wsprintf(buffer, L" %d - %d = %d", i_numberA, i_numberB, i_result);
	else if (Operator == 3)
		wsprintf(buffer, L" %d x %d = %d", i_numberA, i_numberB, i_result);
	else if (Operator == 4)
		wsprintf(buffer, L" %d : %d = %d", i_numberA, i_numberB, i_result);
	else if (Operator == 5)
		wsprintf(buffer, L" %d mod %d = %d", i_numberA, i_numberB, i_result);

}
void Question(int &i_numberA, int &i_numberB, int &i_operator,int &i_result ){
	srand(time(NULL));
	i_numberA = 1 + rand() % 21;
	srand(time(NULL));
	i_numberB = 1 + rand() % 21;
	if (i_numberA%i_numberB == 0 && i_numberA > i_numberB)
		i_operator = 4;
	else if (i_numberA%i_numberB != 0 && i_numberA > i_numberB)
		i_operator = 5;
	else
		srand(time(NULL));  i_operator = 1 + rand() % 4;

	if(i_operator==1)
		i_result =i_numberA + i_numberB ;
	else if (i_operator == 2)
		i_result = i_numberA - i_numberB;
	else if (i_operator == 3)
		i_result = i_numberA * i_numberB;
	else if (i_operator == 4)
		i_result = i_numberA / i_numberB;
	else if(i_operator == 5)
		i_result = i_numberA % i_numberB;
}
void VitualNumber(int &i_vitualNumber, int &i_result) {
	srand(time(NULL));
	i_vitualNumber = i_result + 1 + rand() % 5;
}
int RanDomNumberAToNumberB(int NumberA, int NumberB) {
	srand(time(NULL));
	return NumberA + rand() % NumberB - NumberA + 1;
}
//Ghi và đọc điểm số cao nhất
void WriteHightScore()
{
	FILE *f;
	f = fopen("hightscore.txt", "wt");
	fprintf(f, "%d", i_hightScore);
	fclose(f);
}
void ReadHightScore()
{
	FILE *f;
	f = fopen("hightscore.txt", "rt");
	if (EOF) {
		fscanf(f, "%d", &i_hightScore);
	}
	else
		i_hightScore = 0;
	
	fclose(f);
}