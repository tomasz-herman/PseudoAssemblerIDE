;PROGRAM DO WYZNACZANIA MINIMALNEGO I MAKSYMALNEGO ELEMENTU Z 16-ELEMENTOWEGO WEKTORA
JEDEN:      DC      INTEGER(1)
ROZMIAR:    DC      INTEGER(16)
MAXVALUE:   DC      INTEGER(1000)
SEPARATOR:  DC      CHAR(';')
NEWLINE:    DC      CHAR('\n')
WEKTOR:     DS      16*INTEGER
MIN:        DS      INTEGER
MAX:        DS      INTEGER

MAIN:
        LDA     0, WEKTOR       ;r0 - adres tablicy
        LD      1, ROZMIAR      ;r1 - rozmiar tablicy
        LD      2, MAXVALUE
        NEG     2               ;r2 - minimalna możliwa wygenerowana wartość(-maxvalue)
        LD      3, MAXVALUE     ;r3 - maksymalna -||-
        CALL    INIT_TAB        ;generowanie wartości z przedziału <r2, r3) w tablicy r0 o rozmiarze r1
        LDB     2, SEPARATOR
        CALL    PRINT_TAB
        CALL    MIN_MAX
        ST      2, MIN
        ST      3, MAX
        COUT    NEWLINE
        IOUT    MIN
        COUT    NEWLINE
        IOUT    MAX
        EXIT


;ZNAJDUJE MINIMALNY I MAKSYMALNY ELEMENT WEKTORA
;ARGUMENTY:
;REJESTR 0 - TAB POINTER
;REJESTR 1 - TAB_SIZE
;OPERUJE NA:
;REJESTRY 0, 1, 2, 3, 4
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - WEJŚCIOWY ROZMIAR TABLICY
;REJESTR 2 - MINIMALNY ELEMENT
;RESESTR 3 - MAKSYMALNY ELEMENT
MIN_MAX:
            PUSH    0
            PUSH    1
            LD      2, 0(0)
            LD      3, 0(0)
            LD      4, 0(0)
L3:
            CMP     2, 4
            JLE     SKIP1
            LD      2, 4
SKIP1:
            CMP     3, 4
            JGE     SKIP2
            LD      3, 4
SKIP2:
            LDA     0, 0(4)
            LD      4, 0(0)
            LOOP    1, L3
            POP     1
            POP     0
            RET

;WYŚWIETLA ZAWARTOŚĆ TABLICY
;ARGUMENTY:
;REJESTR 0 - TAB POINTER
;REJESTR 1 - TAB_SIZE
;REJESTR 2 - SEPARATOR
;OPERUJE NA:
;REJESTRY 0, 1
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - WEJŚCIOWY ROZMIAR TABLICY
PRINT_TAB:
            PUSH    0
            PUSH    1
L2:         IOUT    0(0)
            COUT    2
            LDA     0, 0(4)
            LOOP    1, L2
            POP     1
            POP     0
            RET

;INICJALIZUJE TABLICĘ WARTOŚCIAMI OD MINVALUE(INCLUSIVE) DO MAXVALUE(EXLUSIVE)
;ARGUMENTY:
;REJESTR 0 - TAB POINTER
;REJESTR 1 - TAB_SIZE
;REJESTR 2 - MINVALUE
;REJESTR 3 - MAXVALUE
;OPERUJE NA:
;REJESTRY 1, 2, 4, 5
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - WEJŚCIOWY POINTER NA TAB
;REJESTR 1 - WEJŚCIOWY ROZMIAR TABLICY
INIT_TAB:
            PUSH    0
            PUSH    1
            LD      4, 3
            SUB     4, 2
L1:         RAND    5
            DIV     5, 4
            LD      5, 8
            ADD     5, 2
            ST      5, 0(0)
            LDA     0, 0(4)
            LOOP    1, L1
            POP     1
            POP     0
            RET