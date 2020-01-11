;PERFORMS MONTE-CARLO SIMULATIONS TO ESTIMATE PI VALUE
RANDOM_MASK:    DC      INTEGER(0xffffff)
RANDOM_DIST:    DC      INTEGER(0x1000000)
FLOAT_ONE:      DC      FLOAT(1)
FLOAT_FOUR:     DC      FLOAT(4)
NEWLINE:        DC      CHAR('\n')
N:              DC      INTEGER(100)


MAIN:
                LD      3, N
                XOR     1, 1            ;R1 - PUNKTY W ŚRODKU OKRĘGU
                XOR     2, 2            ;R2 - WSZYTKIE PUNKTY
LOOP:
                CALL    RANDOM_POINT
                CALL    IS_INSIDE
                ADD     1, 0
                INC     2
                CALL    APPROXIMATE
                FOUT    0
                COUT    NEWLINE
                LOOP    3, LOOP
                EXIT


;OBLICZA PRZYBLIŻENIE PI NA PODSTAWIE ILOŚCI PUNKTÓW
;W ŚRODKU I WSZYSTKICH
;ARGUMENTY:
;REJESTR 1 - LICZBA PUNKTÓW W ŚRODKU
;REJESTR 2 - LICZBA PUNKTÓW NA ZEWNĄTRZ
;OPERUJE NA:
;REJESTRY 0, F1
;WYMAGA DEKLARACJI:
;FLOAT_FOUR - FLOAT(4)
;ZWRACA:
;REJESTR F0 - PRZYBLIŻENIE PI
APPROXIMATE:
                PUSH    1
                FILD    0, 11(0)
                PUSH    2
                FILD    1, 11(0)
                POP     0
                POP     0
                FDIV    0, 1
                FMUL    0, FLOAT_FOUR
                RET

;BADA CZY PODANY PUNKT LEŻY W ŚRODKU OKRĘGU
;O JEDNOSTKOWYM PROMIENIU
;ARGUMENTY:
;REJESTR F0 - WSPÓŁRZĘDNA X PUNKTU
;REJESTR F1 - WSPÓŁRZĘDNA Y PUNKTU
;OPERUJE NA:
;REJESTRY 0, F1
;WYMAGA DEKLARACJI:
;FLOAT_ONE - FLOAT(1)
;DIST_SQUARED
;ZWRACA:
;REJESTR 0 - JEDEN JEŻELI W ŚRODKU, ZERO WPP
IS_INSIDE:
                CALL    DIST_SQUARED
                XOR     0, 0
                FCMP    0, FLOAT_ONE
                JAE     OUTSIDE
                INC     0
OUTSIDE:        RET

;OBLICZA KWADRAT ODLEGŁOŚCI WSKAZANEGO PUNKTU OD (0, 0)
;ARGUMENTY:
;REJESTR F0 - WSPÓŁRZĘDNA X PUNKTU
;REJESTR F1 - WSPÓŁRZĘDNA Y PUNKTU
;OPERUJE NA:
;REJESTRY F1
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR F0 - ODLEGŁOŚĆ PODNIESIONA DO KWADRATU
DIST_SQUARED:
                FMUL    0, 0
                FMUL    1, 1
                FADD    0, 1
                RET

;GENERUJE LOSOWY PUNKT NA KWADRACIE (0,0),(1,0),(1,1),(0,1)
;ARGUMENTY:
;---
;OPERUJE NA:
;REJESTR 0
;WYMAGA DEKLARACJI:
;RANDOM_FLOAT
;ZWRACA:
;REJESTR F0 - WSPÓŁRZĘDNA X PUNKTU
;REJESTR F1 - WSPÓŁRZĘDNA Y PUNKTU
RANDOM_POINT:
                CALL    RANDOM_FLOAT
                FLD     1, 0
                CALL    RANDOM_FLOAT
                RET

;GENERUJE LOSOWĄ LICZBĘ Z PRZEDZIAŁU <0,1)
;ARGUMENTY:
;---
;OPERUJE NA:
;REJESTR 0
;WYMAGA DEKLARACJI:
;RANDOM_MASK - FLOAT(0xffffff)
;RANDOM_DIST - FLOAT(0x1000000)
;ZWRACA:
;REJESTR F0 - LICZBA Z PRZEDZIAŁU <0,1)
RANDOM_FLOAT:
                RAND    0
                AND     0, RANDOM_MASK
                PUSH    0
                FPOP    0
                FDIV    0, RANDOM_DIST
                RET