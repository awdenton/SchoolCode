;This is my own work: Aaron Denton
;Read in ten integers from 1-1000 (check to make sure entries are valid)
;Display the array
;Sort from smallest to largest
;Display sorted result
;Then display any multiples of three (can use multiple display windows)

.586
.MODEL FLAT

INCLUDE io.h            ; header file for input/output

.STACK 4096

.DATA
intArray DWORD 10 DUP(?)
inPrompt BYTE "Enter an integer between 1-1000 inclusive", 0
string BYTE 40 DUP (?)
outLbl1 BYTE "The array as entered", 0
outLbl2 BYTE "The sorted array", 0
outLbl3 BYTE "Multiples of three in the array", 0
errLbl BYTE "ERROR", 0
errMsg BYTE "Please re-enter a valid integer", 0
arrayStr BYTE 110 DUP (?), 0
passChecks DWORD ?
multThree DWORD	3



.CODE
_MainProc PROC

			;read in the 10 integers
		mov ECX, 10						;intialize loop counter to 10
		lea ESI, intArray				;get the starting address of the integer array
readIn:	input inPrompt, string, 40		;prompt the user for an integer
		atod string						;convert input to a DWORD
			
			;make sure input is valid
		cmp EAX, 1						;check EAX against lower bound
		jl retry						;if value is less than 1, jump to error message
		cmp EAX, 1000					;check EAX against upper bound
		jle resume						;if value is less than or equal 1000, continue program
retry:	output errLbl, errMsg			;display error message for invalid values
		jmp readIn						;jump back to input prompt for a new value

			;store value if valid
resume:	mov [ESI], EAX					;place the DWORD into place in the array
		add ESI, 4						;advance the array address
		loop readIn						;get next value



			;display the array as entered
		mov ECX, 10						;intialize loop counter to 10
		lea ESI, intArray				;get the starting address of the integer array
		lea EDI, arrayStr				;get the starting address of the outut string
disp1:	dtoa [EDI],[ESI]				;convert the value in the int array to a string, place in output string
		add ESI, 4						;advance the int array address
		add EDI, 11						;advance the output string address
		loop disp1						;repeat
		output outLbl1, arrayStr		;display result


			
			;BUBBLE SORT
			;using a Bubble Sort, sort the array from smallest to largest
		mov ECX, 9						;initialize loop counter to 9, the number of passes through the array needed to sort it
		lea EDI, intArray				
		add EDI, 36						;set EDI to point to the last address of the array so we can check if a pass through has finished
			
			;compare two adjacent array values to find which is larger
nxtPass:lea ESI, intArray				;get the starting address of the integer array
		mov passChecks, 0				;reset the number of checks for this pass to 0
nxtComp:inc passChecks					;increment the number of checks for the current pass
		mov EAX, [ESI]					;move the initial value of the array to EAX for comparison
		mov EBX, [ESI+4]				;move the second value of the array to EBX for comparison
										;we load both values to the CPU to ease switching them if we have to

		cmp EAX, EBX					;compare EAX (the first element) with EBX (the second element)
		jng passEnd						;if EAX is not larger than EBX, jump ahead to check if the current pass has reached the end
		
		mov [ESI], EBX					;otherwise, switch the values before continuing to see in the pass has finished
		mov [ESI+4], EAX
		
			;check if the current pass has finished
passEnd:add ESI, 4						;move to the next address in the array
		cmp passChecks, ECX				;compare the number of checks in this pass to the current val in ECX for equality
		jne nxtComp						;if they aren't, we havent reached the end of the pass yet and need to compare the next two values

		sub EDI, 4						;otherwise, decrement EDI, since the last value in the array is now properly sorted
		loop nxtPass					;do the next pass of the bubble sort



			;display the sorted array
		mov ECX, 10						;intialize loop counter to 10
		lea ESI, intArray				;get the starting address of the integer array
		lea EDI, arrayStr				;get the starting address of the outut string
disp2:	dtoa [EDI],[ESI]				;convert the value in the int array to a string, place in output string
		add ESI, 4						;advance the int array address
		add EDI, 11						;advance the output string address
		loop disp2						;repeat
		output outLbl2, arrayStr		;display result



			;look for multiples of three
		mov ECX, 10						;initialize loop counter to 10
		lea ESI, intArray				;set ESI to point at the beginning of the array
		lea EDI, arrayStr				;set EDI to use our same output string

			;prepare for division
threes:	mov EAX, [ESI]					;move the value in ESI to EAX
		mov EDX, 0						;set EDX to 0
		div multThree					;divide the value in EAX by three

		cmp EDX, 0						;check if EDX is 0, indicating no remainder, or that EAX was divisible by three
		jne ahead						;if there is something in EDX, jump ahead to check the next value in array

		dtoa [EDI],[ESI]				;otherwise, convert the value in the int array to a string, place in output string
		add EDI, 11						;advance the output string address

ahead:	add ESI, 4						;advance the int array address
		loop threes						;check the next value in the array
		
		mov DWORD PTR [EDI], 0			;end the arrayStr with a null character to prevent it from printing more information
		output outLbl3, arrayStr		;display the multiples of three found




        mov     eax, 0  ; exit with return code 0
        ret
_MainProc ENDP
END                             ; end of source code

