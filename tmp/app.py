from flask import Flask, request

app = Flask(__name__)


@app.route('/', methods=['GET', 'POST'])
def home():
    import ipdb
    ipdb.set_trace()
    print 'received'
    return "hi"

if __name__ == '__main__':
    app.run(debug=True)
