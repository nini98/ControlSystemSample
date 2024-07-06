const fetchUtil = {

    // handleResponse: async (response) => {
    //     const jsonData = await response.json();
    //     if (!response.ok) {
    //         const error = new Error(jsonData.header.resultMessage);
    //         error.header = jsonData.header;
    //         error.data = jsonData.data;
    //         throw error;
    //     }
    //     return jsonData;
    // },

    handleResponse: async (response) => {
        const contentType = response.headers.get('content-type');
        if (contentType && contentType.indexOf('application/json') !== -1) {
            const jsonData = await response.json();
            if (!response.ok) {
                const error = new Error(jsonData.message || jsonData.header.resultMessage);
                error.header = jsonData.header;
                error.data = jsonData.data;
                throw error;
            }
            return jsonData;
        } else {
            return response.text(); // JSON이 아닌 응답은 텍스트로 처리
        }
    },

    postJson: async (url, body) => {
        try {
            const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    [csrfHeader]: csrfToken
                },
                body: JSON.stringify(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    putJson: async (url, body) => {
        try {
            const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

            const response = await fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    [csrfHeader]: csrfToken
                },
                body: JSON.stringify(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    deleteJson: async (url, body) => {
        try {
            const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

            const response = await fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    [csrfHeader]: csrfToken
                },
                body: JSON.stringify(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    getUrlEncoded: async (url, params) => {
        try {
            const queryString = new URLSearchParams(params).toString();
            const response = await fetch(`${url}?${queryString}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    postUrlEncoded: async (url, body) => {
        try {
            const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    [csrfHeader]: csrfToken
                },
                body: new URLSearchParams(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    putUrlEncoded: async (url, body) => {
        try {
            const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

            const response = await fetch(url, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    [csrfHeader]: csrfToken
                },
                body: new URLSearchParams(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    deleteUrlEncoded: async (url, body) => {
        try {
            const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

            const response = await fetch(url, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    [csrfHeader]: csrfToken
                },
                body: new URLSearchParams(body),
            });
            return await fetchUtil.handleResponse(response);
        } catch (error) {
            throw error;
        }
    },

    getBlob: async (url, params) => {
        try {
            const queryString = new URLSearchParams(params).toString();
            const response = await fetch(`${url}?${queryString}`, {
                method: 'GET'
            });
            if (!response.ok) {
                const error = new Error('Network response was not ok');
                throw error;
            }
            return response.blob();
        } catch (error) {
            throw error;
        }
    },
};