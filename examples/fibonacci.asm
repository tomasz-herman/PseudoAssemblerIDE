;BARDZIEJ SKOMPLIKOWANY PRZYKŁAD POKAZUJĄCY REKURENCJĘ W PSEUDOASSEMBLERZE
DWA:         DC  INTEGER(2)
ARG:         DC  INTEGER(32)

MAIN:
        LD      0, ARG          ;fibonacci(32) DAJ FUNKCJI ARGUMENT
        CALL    FIBONACCI_REK  ;WYWOŁAJ FUNKCJĘ
        OUT     0               ;printf(wynik)
        EXIT


;ITERACYJNIE OBLICZA N-TY WYRAZ CIĄGU FIBONACCIEGO
;ZŁOŻONOŚĆ LINIOWA
;ARGUMENTY:
;REJESTR 0 - N
;OPERUJE NA:
;REJESTRY 0, 1
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;N-TY WYRAZ CIĄGU FIBONACCIEGO - REJESTR 0
FIBONACCI_ITER:
        LD      1, 0
        XOR     2, 2
        LD      3, 2
        LD      4, 3
        INC     4
        INC     0
LOOP:
        LD      2, 3
        LD      3, 4
        LD      4, 3
        ADD     4, 2
        LOOP    0, LOOP
        LD      0, 2
        RET


;REKURENCYJNIE OBLICZA N-TY WYRAZ CIĄGU FIBONACCIEGO
;BARDZO ZŁA ZŁOŻONOŚĆ(A LA FIBONACCI)
;ARGUMENTY:
;REJESTR 0 - N
;OPERUJE NA:
;REJESTRY 0, 1
;WYMAGA DEKLARACJI:
;DWA - INTEGER(2)
;ZWRACA:
;N-TY WYRAZ CIĄGU FIBONACCIEGO - REJESTR 0
FIBONACCI_REK:
        PUSH    0
        CALL    REKURENCJA
        POP     0
        LD      0, 1
        RET

REKURENCJA:
        LD      0, 11(4)        ;ZAPISZ ARGUMENT FUNKCJI W REJESTRZE 0
        CMP     0, DWA          ;if(n<2)return n;
        JB      STOP_FIBONACCI_REK
        DEC     0               ;n--
        PUSH    0               ;DAJ NASTĘPNEJ REKURENCJI ARGUMENT
        CALL    REKURENCJA      ;fibonacci(n)
        POP     0               ;ZDEJMIJ ZE STOSU ARGUMENT POPRZEDNIEGO WYWOŁANIA
        DEC     0               ;n--
        PUSH    1               ;ZAPAMIĘTAJ WYNIK ZWRÓCONY PRZEZ REKURENCJĘ NA STOSIE
        PUSH    0               ;DAJ NASTĘPNEJ REKURENCJI ARGUMENT
        CALL    REKURENCJA      ;fibonacci(n)
        POP     0               ;ZDEJMIJ ZE STOSU ARGUMENT POPRZEDNIEGO WYWOŁANIA
        POP     0               ;ZDEJMIJ ZE STOSU WYNIK Z POPRZEDNIEJ REKURENCJI
        ADD     1, 0            ;DODAJ DO SIEBIE WYNIKI OBU REKURENCJI
        RET                     ;return fibonacci(n-1) + fibonacci(n-2) ZOSTAW WYNIK W REJESTRZE 1
STOP_FIBONACCI_REK:
        LD      1, 0            ;n<2 -> return n ZAPISZ WYNIK W REJESTRZE 1
        RET
