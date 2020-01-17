; This is my own work: Aaron Denton

.586
.MODEL FLAT

INCLUDE io.h            ; header file for input/output

.STACK 4096

.DATA
posInt DWORD   ?
prompt BYTE "Enter a positive integer", 0
errLbl BYTE	"ERROR", 0
errMsg BYTE "Please enter a valid positive integer", 0
string  BYTE    40 DUP (?)

resultLbl BYTE "Result:", 0
resultMsg BYTE "The sum series of "
intStr BYTE 11 DUP (?)
BYTE " is:"
sumStr BYTE 11 DUP (?), 0

sumResult DWORD 0

.CODE
_MainProc PROC
retry:  input   prompt, string, 40			; read ASCII characters
        atod    string						; convert to integer
		cmp		EAX, 1						; check the input against the minimum acceptable value
		jge		valid						; if it is valid, jump ahead and store it
		output	errLbl, errMsg				; if it isnt, display error message
		jmp		retry						; jump back and get a new value
valid:	mov     posInt, EAX					; store value in memory

			;call the sub-routine sumseries
		push	posInt						; push the input value onto the stack
		call	sumseries					; call the sumseries procedure
		add		ESP, 4						; de-allocate the space taken by the push

			;prepare the result display
		dtoa	sumStr, EAX					; convert the result of sumseries to a string
		dtoa	intStr, posInt				; convert original input to a string
		output	resultLbl, resultMsg		; display result message


        mov     eax, 0						; exit with return code 0
        ret
_MainProc ENDP


; For a given integer i, returns the sum of (1*2)+(2*3)+(3*4)+...+(i*(i+1)) as a DWORD
; Takes one DWORD as an input
sumseries PROC
		push	EBP							; save the EBP
		mov		EBP, ESP					; establish the stack frame
		mov		EAX, [EBP + 8]				; move the input value into EAX

			;check for the base case
		cmp		EAX, 1						; check for the base case, where i=1
		jg		recur						; if the input is greater than 1, jump to the recursive step
		
			;the base case
		add		sumResult, 2				; add 2 to the sumResult (the base case of 1*2)
		mov		EAX, sumResult				; move the sumResult to EAX to return it
		pop		EBP							; restore the EBP
		ret									; return to the calling method

			;the recursive step
recur:	push	EBX							; store the value in EBX
		mov		EBX, EAX					; move input value into EBX
		inc		EBX							; increment EBX (EAX now equals i, EBX equals i+1)
		imul	EBX, EAX					; multiply EBX and EAX, storing result in EBX
		add		sumResult, EBX				; add the value in EBX to the result
		
		dec		EAX							; decrement EAX (i-1)
		push	EAX							; store EAX as the input for the next recursive call
		call	sumSeries					; the recursive call
		add		ESP, 4						; de-allocate the space used by pushing EAX

		mov		EAX, sumResult				; place the final sum into EAX to return it

		pop		EBX							; restore the value in EBX
		pop		EBP							; restore the EBP
		ret									; return to calling method
sumseries ENDP

END                             ; end of source code

