HELLO:          DC      STRING("Hello! What is your name?\n")
WELL:           DC      STRING("Well, ")
THINKING:       DC      STRING(", I am thinking of a number between 1 and 1000.\n")
GUESS:          DC      STRING("Take a guess.\n")
TOO_HIGH:       DC      STRING("Your guess is too high.\n")
TOO_LOW:        DC      STRING("Your guess is too low.\n")
NOT_A_NUMBER:   DC      STRING("Your guess is not a number.\n")
GOOD_JOB:       DC      STRING("Good job, ")
GUESSED_IN:     DC      STRING("! You guessed my number in ")
GUESSES:        DC      STRING(" guesses!\n")
TRIES:          DS      INTEGER
NUMBER:         DS      INTEGER
MAX_NUMBER:     DC      INTEGER(1000)
NAME:           DS      21*CHAR
MAX_NAME_LEN:   DC      INTEGER(20)
NEW_LINE:       DC      CHAR('\n')
ZERO_CHAR:      DC      CHAR('0')

MAIN:
                LD      0, MAX_NUMBER
                CALL    GENERATE_NUMBER
                ST      1, NUMBER
                LDA     0, HELLO
                CALL    PRINT
                LDA     0, NAME
                LD      1, MAX_NAME_LEN
                CALL    INPUT_NAME
                LDA     0, WELL
                CALL    PRINT
                LDA     0, NAME
                CALL    PRINT
                LDA     0, THINKING
                CALL    PRINT
                CALL    GAME
                EXIT

GAME:
                XOR     7, 7
                ST      7, TRIES
TAKE_A_GUESS:
                INC     TRIES
                LDA     0, GUESS
                CALL    PRINT
                CALL    INPUT_NUMBER
                CMP     1, 7
                JL      NAN
                CMP     1, NUMBER
                JL      LESSER
                JG      GREATER
                JE      EQUALITY
NAN:
                LDA     0, NOT_A_NUMBER
                CALL    PRINT
                JMP     TAKE_A_GUESS
LESSER:
                LDA     0, TOO_LOW
                CALL    PRINT
                JMP     TAKE_A_GUESS
GREATER:
                LDA     0, TOO_HIGH
                CALL    PRINT
                JMP     TAKE_A_GUESS
EQUALITY:
                LDA     0, GOOD_JOB
                CALL    PRINT
                LDA     0, NAME
                CALL    PRINT
                LDA     0, GUESSED_IN
                CALL    PRINT
                OUT     TRIES
                LDA     0, GUESSES
                CALL    PRINT
                RET


GENERATE_NUMBER:
                RAND    1
                DIV     1, 0
                LD      1, 8
                INC     1
                RET

PRINT:
                PUSH    0
                XOR     1, 1
_LOOP1:
                LDB     2, 0(0)
                CMP     2, 1
                JZ      _STOP1
                COUT    2
                LDA     0, 0(1)
                JMP     _LOOP1
_STOP1:
                POP     0
                RET

INPUT_NAME:
                PUSH    0
                LDB     3, NEW_LINE
_LOOP2:         IN      2
                CMP     2, 3
                JE      _STOP2
                STB     2, 0(0)
                LDA     0, 0(1)
                LOOP    1, _LOOP2
FLUSH:          IN      2
                CMP     2, 3
                JNE     FLUSH
_STOP2:

                XOR     2, 2
                STB     2, 0(0)
                POP     0
                RET

INPUT_NUMBER:
                LDB     3, NEW_LINE
                XOR     1, 1
                XOR     4, 4
                LDB     5, ZERO_CHAR
_LOOP3:         IN      2
                CMP     2, 3
                JE      _STOP3
                SUB     2, 5
                CMP     2, 4
                JL      _NAN
                CMP     2, 3
                JGE     _NAN
                MUL     1, 3
                ADD     1, 2
                JMP     _LOOP3
_NAN:
                LD      1, 4
                DEC     1
_FLUSH:         IN      2
                CMP     2, 3
                JNE     _FLUSH
_STOP3:
                RET