
from flask import Flask, render_template, jsonify, request
import random

app = Flask(__name__)

# A list of possible words for the game (just a few for demo purposes)
word_list = ['apple', 'berry', 'charm', 'delta', 'eagle']

# Game state (this would normally be more complex in a real deployment)
correct_word = random.choice(word_list)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/check_word', methods=['POST'])
def check_word():
    guess = request.json.get('guess').lower()
    result = []

    if guess == correct_word:
        return jsonify({"status": "win", "correct_word": correct_word})

    for i in range(len(guess)):
        if guess[i] == correct_word[i]:
            result.append('green')  # Correct letter and position
        elif guess[i] in correct_word:
            result.append('yellow')  # Correct letter, wrong position
        else:
            result.append('grey')    # Incorrect letter

    return jsonify({"status": "ongoing", "result": result})

if __name__ == '__main__':
    app.run(debug=True)
