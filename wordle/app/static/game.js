
document.getElementById("submit-guess").addEventListener("click", function() {
    let guess = document.getElementById("guess-input").value;

    fetch("/check_word", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ guess: guess }),
    })
    .then(response => response.json())
    .then(data => {
        if (data.status === "win") {
            document.getElementById("result").textContent = "You win! The word was: " + data.correct_word;
        } else {
            document.getElementById("result").textContent = "";
            let currentRow = document.querySelectorAll(".row")[6 - guessCount];
            let squares = currentRow.querySelectorAll(".square");

            for (let i = 0; i < guess.length; i++) {
                squares[i].textContent = guess[i];
                squares[i].classList.add(data.result[i]);
            }
            guessCount--;
        }
    });
});

let guessCount = 6;  // Number of guesses left
