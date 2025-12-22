; ================================
; AutoIT: Upload file dynamic
; Nhận file path từ tham số dòng lệnh
; Dùng được cho Chrome / Edge / Firefox
; ================================

If $CmdLine[0] < 1 Then
    MsgBox(0, "Error", "Thiếu tham số file upload!")
    Exit
EndIf

$filePath = $CmdLine[1]

; Chờ cửa sổ Open xuất hiện (tiêu đề "Open" hoặc "File Upload")
; Tùy Windows, bạn có thể cần sửa title cho khớp

Local $hWnd = WinWaitActive("[CLASS:#32770]", "", 10)

If $hWnd = 0 Then
    MsgBox(0, "Error", "Không tìm thấy cửa sổ upload!")
    Exit
EndIf

; Focus vào textbox đường dẫn
ControlFocus($hWnd, "", "Edit1")
ControlSetText($hWnd, "", "Edit1", $filePath)

; Click nút Open
ControlClick($hWnd, "", "Button1")
